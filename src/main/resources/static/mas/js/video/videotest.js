layui.use(['layer', 'form'], function () {
    var layer = layui.layer
        , $ = layui.jquery;


    // 表单数据
    var form = layui.form;

    var i;

// var form = document.getElementById("checkboxLabel");

// // 初始化页面
//     $.ajax({
//         type: "GET",
//         url: "/search/submitsearch",
//         dataType: "json",
//         success: function (data) {
//             // console.log(data.videoID);
//             console.log(data)
//             if (data === null) {
//                 layer.open({
//                     type: 1
//                     , title: false //不显示标题栏
//                     , closeBtn: false
//                     , area: '300px;'
//                     , shade: 0.8
//                     , id: 'LAY_layuipro_1' //设定一个id，防止重复弹出
//                     // ,btn: ['前往任务列表']
//                     , btnAlign: 'c'
//                     , moveType: 1 //拖拽模式，0或者1
//                     , content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; ' +
//                         'color: #fff; font-weight: 300;">无任务信息请去任务列表领取</div>' +
//                         '<a  href="/static/mas/html/task/task.html" class="layui-btn layui-btn layui-btn-xs" lay-event="continue">前往任务列表</a>'
//                     // ,success: function(layero){
//                     //     var btn = layero.find('.layui-layer-btn');
//                     //     btn.find('.layui-layer-btn0').attr({
//                     //         href: '/static/dpmark/html/task/task.html'
//                     //         ,target: '_blank'
//                     //     });
//                     // }
//                 });
//             } else {
//                 var src = data.filePath
//                 var arr = src.split(".\/");
//                 console.log(arr[0], "\n", arr[1]);
//                 var address = "\/" + arr[1];
//                 console.log(address);
//                 // myVideo.src = "\\" + arr[1];
//                 // console.log(myVideo.src);
//             }
//         }
//     });

    $(document).on('click', '#search-drug', function () {
        //clearInterval(i);
        submitData();
    });




    /*
     * 提交数据
     */

    var labelText;
    // form.on('submit(demo1)', function (data1) {
    //     // labelText = data1.field;
    //     labelText = JSON.stringify(data1.field);
    //     // alert(JSON.stringify(labelText));
    //     return false;
    // });


    function submitData() {
        labelText = document.getElementById("find_content").value;
        //window.clearInterval();


        $.ajax({
            type: "POST",
            url: "/search/submitsearch",
            dataType: "json",
            contentType: 'application/json',
            data: JSON.stringify({name:labelText}),
            success: function (data) {
                console.log(data);
                //alert(JSON.stringify({name:labelText}));
                if (data == null) {
                    layer.msg("未找到相关词");
                } else {
                    //document.getElementById("result_content").value=JSON.stringify(data);
                    document.getElementById("result_content").value=data;
                    //layer.msg(data);
                }
            },
            error:function(XMLHttpRequest, textStatus, errorThrown) {
                console.log("sss");
                console.log(data);
            }
        })
    }


})
;

