layui.use(['layer', 'form'], function () {
    var layer = layui.layer
        , $ = layui.jquery;


    // 表单数据
    var form = layui.form;

    var i;

    $(document).on('click', '#add-relation', function () {
        addData();
    });

    /*
     * 提交数据
     */

    var lentity1,lentity2,entity1,entity2,relation;

    function addData() {
        lentity1 = document.getElementById("sentity1").value;
        lentity2= document.getElementById("sentity2").value;
        entity1= document.getElementById("entity1_content").value;
        entity2= document.getElementById("entity2_content").value;
        relation= document.getElementById("relation_content").value;


        $.ajax({
            type: "POST",
            url: "/add/addrelation",
            dataType: "json",
            contentType: 'application/json',
            data: JSON.stringify({label1:lentity1,label2:lentity2,entity1:entity1,entity2:entity2,relation:relation}),
            success: function (data) {
                console.log(data);
                //alert(JSON.stringify({name:labelText}));
                if (data == null) {
                    layer.msg("添加失败");
                } else {
                    layer.msg("添加成功");
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

