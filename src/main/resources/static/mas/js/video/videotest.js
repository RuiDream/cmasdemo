layui.use(['layer', 'form'], function () {
    var layer = layui.layer
        , $ = layui.jquery;
    // 播放窗口
    var myVideo = document.getElementById("dpmark-video");

    // 面板数据
    var startx = document.getElementById("dpmark-video-data-startx")
        , starty = document.getElementById("dpmark-video-data-starty")
        , diffx = document.getElementById("dpmark-video-data-diffx")
        , diffy = document.getElementById("dpmark-video-data-diffy")
        , starttime = document.getElementById("dpmark-video-data-starttime")
        , stoptime = document.getElementById("dpmark-video-data-stoptime");
    // 视频ID
    var videoID;

    // 表单数据
    var form = layui.form;

    var i;

// var form = document.getElementById("checkboxLabel");

// 初始化页面
    $.ajax({
        type: "GET",
        url: "/label/getTaskVideo",
        dataType: "json",
        success: function (data) {
            // console.log(data.videoID);
            console.log(data)
            if (data === null) {
                layer.open({
                    type: 1
                    , title: false //不显示标题栏
                    , closeBtn: false
                    , area: '300px;'
                    , shade: 0.8
                    , id: 'LAY_layuipro_1' //设定一个id，防止重复弹出
                    // ,btn: ['前往任务列表']
                    , btnAlign: 'c'
                    , moveType: 1 //拖拽模式，0或者1
                    , content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; ' +
                        'color: #fff; font-weight: 300;">无任务信息请去任务列表领取</div>' +
                        '<a  href="/static/dpmark/html/task/task.html" class="layui-btn layui-btn layui-btn-xs" lay-event="continue">前往任务列表</a>'
                    // ,success: function(layero){
                    //     var btn = layero.find('.layui-layer-btn');
                    //     btn.find('.layui-layer-btn0').attr({
                    //         href: '/static/dpmark/html/task/task.html'
                    //         ,target: '_blank'
                    //     });
                    // }
                });
            } else {
                var src = data.filePath
                var arr = src.split(".\/");
                console.log(arr[0], "\n", arr[1]);
                var address = "\/" + arr[1];
                console.log(address);
                myVideo.src = address;
                // myVideo.src = "\\" + arr[1];
                // console.log(myVideo.src);
                videoID = data.videoID;
            }
        }
    });

    $(document).on('click', '#dpmark-video-controller-startmark', function () {
        myVideo.pause();
        layer.msg('点击鼠标左键并拖动绘制矩形,' +
            '并使用画笔在矩形上进行标注', {
            time: 2000, //2s后自动关闭
        });
        draw_box();
        add_cover();
    });

    $(document).on('click', '#dpmark-video-controller-started', function () {
        myVideo.play();
        var timeDisplay = Math.floor(myVideo.currentTime);
        starttime.innerText = timeDisplay;
    });

    $(document).on('click', '#dpmark-video-controller-cancel', function () {
        myVideo.pause();
        var v = document.getElementById("large-video");
        var c = document.getElementById("draw-screen");
        var video_box = document.getElementById("video_box");
        var draw_box = document.getElementById("draw_box");
        v.remove();
        c.remove();
        video_box.remove();
        draw_box.remove();
        clearInterval(i);
        clear_box();
        remove_listener();
        remove_cover();
        clear_data();
    });

    $(document).on('click', '#dpmark-video-controller-success', function () {
        myVideo.pause();
        var timeDisplay = Math.floor(myVideo.currentTime);
        stoptime.innerText = timeDisplay;
        clearInterval(i);
        submitData();
        clear_box();
        remove_listener();
        remove_cover();
        clear_data();
    });

    /*
     * 提交数据
     */

    var labelText;
    form.on('submit(demo1)', function (data1) {
        // labelText = data1.field;
        labelText = JSON.stringify(data1.field);
        // alert(JSON.stringify(labelText));
        return false;
    });


    function submitData() {
        window.clearInterval();
        var labels = [starttime.innerText, stoptime.innerText, startx.innerText, starty.innerText,
            diffx.innerText, diffy.innerText, myVideo.getBoundingClientRect().width, myVideo.getBoundingClientRect().height];

        var totalCanvas = document.createElement('canvas');

        totalCanvas.width = diffx.innerText * 2;
        totalCanvas.height = diffy.innerText * 2;

        var v = document.getElementById("large-video");
        var c = document.getElementById("draw-screen");
        console.log(c);
        ctx = totalCanvas.getContext('2d');

        ctx.drawImage(v, 0, 0, totalCanvas.width, totalCanvas.height, 0, 0, totalCanvas.width, totalCanvas.height);
        if (c != null) {
            ctx.drawImage(c, 0, 0, totalCanvas.width, totalCanvas.height, 0, 0, totalCanvas.width, totalCanvas.height);
        }

        var video_box = document.getElementById("video_box");
        var draw_box = document.getElementById("draw_box");
        v.remove();
        c.remove();
        video_box.remove();
        draw_box.remove();


        var clipImgBase64 = totalCanvas.toDataURL()
        var b64 = clipImgBase64.substring(22);

        for (i in labels) {
            if (labels[i] === '') {
                layer.msg("提交失败，标记区域不全，请重新标记！");
                return
            }
        }
        $.ajax({
            type: "POST",
            url: "/label/submitlabel",
            dataType: "json",
            contentType: 'application/json',
            data: JSON.stringify({videoID: videoID, labelTuple: labels.toString(), imgUrl: b64, labelText: labelText}),
            success: function (data) {
                // alert(JSON.stringify({videoID: videoID, labelText:labelText}));
                if (data === true) {
                    layer.msg("提交成功");
                } else {
                    layer.msg("提交失败，请重试！");
                }
            }
        })
    }


    function draw_box() {
        var e = window.event;
        // startX, startY 为鼠标点击时初始坐标
        // diffX, diffY 为鼠标初始坐标与 box 左上角坐标之差，用于拖动
        var startX, startY, diffX, diffY;
        // 是否拖动，初始为 false
        var dragging = false;
        // 只允许单次
        var once = true;
        var twice = true;

        // 鼠标按下
        document.onmousedown = function (e) {
            // 判断是否重复标记
            if (!once) {
                return
            }
            once = false;

            startX = e.pageX;
            startY = e.pageY;

            console.log(startX, startY, myVideo.getBoundingClientRect().left, myVideo.offsetWidth);
            // 更新面板数据
            startx.innerText = startX - myVideo.getBoundingClientRect().left;
            starty.innerText = startY - myVideo.getBoundingClientRect().top;

            if (startX < myVideo.getBoundingClientRect().left || startX > myVideo.getBoundingClientRect().left + myVideo.offsetWidth
                || startY < myVideo.getBoundingClientRect().top || startY > myVideo.getBoundingClientRect().top + myVideo.offsetHeight) {
                layer.open({
                    type: 1
                    , title: false //不显示标题栏
                    , closeBtn: false
                    , area: '300px;'
                    , shade: 0.8
                    , id: 'LAY_layuipro' //设定一个id，防止重复弹出
                    , btn: ['确定']
                    , btnAlign: 'c'
                    , moveType: 1 //拖拽模式，0或者1
                    , content: '请在视频窗口范围内选取标记区域'
                    , success: function (layero) {
                        clear_box();
                        remove_listener();
                        remove_cover();
                        clear_data();
                    }
                });
                clear_box();
                remove_listener();
                remove_cover();
                clear_data();
            }


            // 在页面创建 box
            var active_box = document.createElement("div");
            active_box.id = "active_box";
            active_box.className = "dpmark-box";
            active_box.style.top = startY + 'px';
            active_box.style.left = startX + 'px';
            document.body.appendChild(active_box);
            console.log(active_box);

            active_box.onclick = function (ev) {
                if (!twice) {
                    return
                }
                twice = false;
                console.log(active_box);

                var box = document.getElementsByClassName("dpmark-box");
                var need_width = box[0].style.width;
                var need_height = box[0].style.height;
                var need_top = box[0].style.top;
                var need_left = box[0].style.left;

                //放大视频层
                var video_box = document.createElement("div");
                video_box.id = "video_box";
                video_box.className = "video-box";
                video_box.style.top = '0px';
                video_box.style.left = '0px';
                document.body.appendChild(video_box);
                console.log(video_box);

                var clipCanvas = document.createElement('canvas');
                clipCanvas.id = 'large-video'
                var sx = parseInt(need_left);
                var sy = parseInt(need_top);
                clipCanvas.width = parseInt(need_width) * 2;
                clipCanvas.height = parseInt(need_height) * 2;
                clipCanvas.left = 0;
                clipCanvas.top = 1;

                ctx = clipCanvas.getContext('2d');

                var v = document.getElementById("dpmark-video");


                i = window.setInterval(function () {
                    ctx.drawImage(v, sx, sy, parseInt(need_width), parseInt(need_height), 0, 0, clipCanvas.width, clipCanvas.height)
                }, 20);

                video_box.appendChild(clipCanvas);

                //画笔层
                var draw_box = document.createElement("div");
                draw_box.id = "draw_box";
                draw_box.className = "draw-box";
                draw_box.style.top = '0px';
                draw_box.style.left = '0px';
                document.body.appendChild(draw_box);
                console.log(draw_box);

                var oC = document.createElement('canvas');
                oC.id = 'draw-screen';
                oC.width = clipCanvas.width;
                oC.height = clipCanvas.height;
                oC.left = clipCanvas.left;
                oC.top = clipCanvas.top;

                var oGC = oC.getContext("2d");
                oC.onmousedown = function (e) {
                    //计算鼠标在画布的距离
                    var disX = e.clientX - oC.left
                    var disY = e.clientY - oC.top
                    //每次必须重新开始，让它们变成多个。
                    oGC.beginPath();

                    //设置画线的宽，与颜色
                    oGC.lineWidth = 1;
                    oGC.strokeStyle = 'black';
                    //设置画的起始点
                    oGC.moveTo(disX, disY)

                    document.onmousemove = function (e) {
                        var disX = e.clientX - oC.left;
                        var disY = e.clientY - oC.top;
                        //移动时设置画线的结束位置。并且显示
                        oGC.lineTo(disX, disY) //鼠标点下去的位置
                        oGC.stroke()
                    }
                    //鼠标离开时清空鼠标按下与移动事件，停止画
                    document.onmouseup = function () {
                        document.onmousedown = null //鼠标放开的位置
                        document.onmousemove = null
                    }
                }
                draw_box.appendChild(oC);
                console.log(oC);

            }
        };

        // 鼠标移动
        document.onmousemove = function (e) {
            // 更新 box 尺寸
            if (document.getElementById("active_box") !== null) {
                var ab = document.getElementById("active_box");
                ab.style.width = e.pageX - startX + 'px';
                ab.style.height = e.pageY - startY + 'px';
                // 更新面板数据
                diffx.innerText = e.pageX - startX;
                diffy.innerText = e.pageY - startY;
            }

            // 移动，更新 box 坐标
            if (document.getElementById("moving_box") !== null && dragging) {
                var mb = document.getElementById("moving_box");
                mb.style.top = e.pageY - diffY + 'px';
                mb.style.left = e.pageX - diffX + 'px';
            }
        };

        // 鼠标抬起
        document.onmouseup = function (e) {
            // 禁止拖动
            dragging = false;
            if (document.getElementById("active_box") !== null) {
                var ab = document.getElementById("active_box");
                // ab.removeAttribute("id");
                ab.id = "unactive_box";
                // remove_listener()
                // 如果长宽均小于 3px，移除 box
                if (ab.offsetWidth < 3 || ab.offsetHeight < 3) {
                    document.body.removeChild(ab);
                }
            }
        };
        return i;
    }

    /*
     * 添加透明遮罩防止误触播放器
     */
    function add_cover() {
        var parent = document.getElementById("dpamrk-video-card");
        var cover = document.createElement("div");
        cover.className = "dpmark-cover";
        cover.id = "dpmark-cover";
        parent.appendChild(cover);
    }

    /*
     * 清除透明罩
     */
    function remove_cover() {

        var cover = document.getElementById("dpmark-cover");
        if (cover != null) {
            cover.remove();
        }
    }

    /*
     * 清除已选box
     */
    function clear_box() {
        var ab = document.getElementById("active_box");
        if (ab != null) {
            ab.remove();
        }
        ab = document.getElementById("unactive_box");
        if (ab != null) {
            ab.remove();
        }
        var mb = document.getElementById("moving_box");
        if (mb != null) {
            mb.remove();
        }

    }

    /*
     * 移除鼠标监听
     */
    function remove_listener() {
        document.removeEventListener("onmousedown", function (evt) {
        })
        document.removeEventListener("onmousemove", function (evt) {
        })
        document.removeEventListener("onmouseup", function (evt) {
        })
    }

    /*
     * 清除数据面板
     */
    function clear_data() {
        starttime.innerText = '';
        stoptime.innerText = '';
        starty.innerText = '';
        startx.innerText = '';
        diffy.innerText = '';
        diffx.innerText = '';

    }

    function downloadIamge(imgUrl) {
        // 生成一个a元素
        var a = document.createElement('a')
        // 创建一个单击事件
        var event = new MouseEvent('click')
        // 生成文件名称
        var timestamp = new Date().getTime();
        var name = imgUrl.substring(22, 30) + timestamp + '.png';
        a.download = name
        // 将生成的URL设置为a.href属性
        a.href = imgUrl;
        console.log(a.href);
        // 触发a的单击事件 开始下载
        a.dispatchEvent(event);

    }

})
;

