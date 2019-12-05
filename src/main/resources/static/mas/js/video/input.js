var vue = new Vue({
    el: '#content',
    data: {
        // 照片文件本地URL
        photoUrl: '',
        // 病人信息
        patient: {
            ID: {
                text: "病人ID",
                value: ''
            },
            name: {
                text: "姓名",
                value: ''
            },
            gender: {
                text: "性别",
                value: ''
            },
            age: {
                text: "年龄",
                value: ''
            },
            depart: {
                text: "科室",
                value: ''
            },
            result: {
                text: "诊断结果",
                value: ''
            },
            remark: {
                text: "备注",
                value: ''
            },
        },
        // 病情描述
        describe: '',
        // 是否已经提交成功
        submitSuccess: false,

        // 病情分析
        result: {
            // 疾病诊断结果
            disease_result: "无",
            // 抑郁
            depression_pro: "0.0%",
            // 躁狂
            mania_pro: "0.0%",
            // 精神分裂
            schizophrenia_pro: "0.0%",
            // 焦虑症
            anxiety_pro: "0.0%"
        },
        // 症状关键词 只显示9个
        keyWords: [],
        // 关键词的颜色 7种
        keyWordsColor: ['rgba(72,61,139', 'rgba(255,106,106',
            'rgba(255,69,0', 'rgba(255,127,36', 'rgba(0,139,139',
            'rgba(125,38,205', 'rgba(67,110,238', 'rgba(46,139,87'
        ],
        // 治疗推荐
        recommends: []
    },
    methods: {
        selectPhoto: function () {
            console.log("select photo");
            // 点击照片，调用file input组件
            if (this.submitSuccess)
                return;
            // 选择照片
            this.$refs.fileInput.click();
        },
        changePhoto: function (e) {
            // 选择的照片文件发生变化时调用此函数
            console.log("photo file change", e.target.files[0]);
            // 获取选择的文件路径
            var file = e.target.files[0];
            if (file == undefined)
                return;
            console.log(file.type);
            if (file.type != "image/jpeg" && file.type != "image/jpg" && file.type != "image/png") {
                alert("请选择jpeg、jpg、png格式的照片");
                return;
            }
            var url = null;
            if (window.createObjectURL != undefined) { // basic
                url = window.createObjectURL(file);
            } else if (window.webkitURL != undefined) { // webkit or chrome
                url = window.webkitURL.createObjectURL(file);
            } else if (window.URL != undefined) { // mozilla(firefox)
                url = window.URL.createObjectURL(file);
            }
            if (url != null) {
                this.photoUrl = url;
            }
        },
        uploadFiles: function () {
            // 上传所有文件
            console.log("upload files");
        },


        submit: function () {
            axios({
                    method: "post",
                    url: "/cure/result",
                dataType: "json",
                contentType: 'application/json',
                    data: JSON.stringify({
                        name: document.getElementById("").value
                    })
                })

                .then(response => {
                    console.log("提交成功", response);
                    var data = response.data;
                    this.result.depression_pro = data.depression_pro;
                    this.result.disease_result = data.disease_result;
                    this.result.mania_pro = data.mania_pro;
                    this.recommends = data.suggestion;
                    this.keyWords = data.symptom_key;
                    if (this.keyWords.length > 9) {
                        this.keyWords.length = 9;
                    }
                })
                .catch((error) => {
                    console.log("提交失败", error);
                })
            var h = window.location.href;
            console.log(h);
            this.submitSuccess = true;
            return;

            // 提交病人信息
            console.log("submit all")
            // 判断是否填写完整
            if (this.photoUrl == "") {
                alert("请上传病人照片")
            }
            for (var name in this.patient) {
                if (name == 'remark') // 备注可以为空
                    continue;
                if (this.patient[name].value == "") {
                    alert(this.patient[name].text + " 不能为空");
                    return;
                }
            }
            if (this.describe == "") {
                alert("症状描述为必填内容");
            }
            // 提交所有内容
            // TODO
            // this.submitSuccess = true;
        }
    },
    computed: {},

})