$(".avatar-upload").on("click","#upload-avatar-btn",function() {
		$("#upload-avatar-input").click();
});

/* 上传头像 */
$("#upload-avatar-input").change(function(){
        $.ajax({
            "url":"/user/change_avatar.do",
            "data":new FormData($("#avatar-form")[0]),
            "processData":false,
            "contentType":false,
            "type":"POST",
            "dataType":"json",
            "success":function(json) {
                if (json.state == 2000) {
                    alert("修改成功！");
                    console.log(json.data);
                    $("#img-avatar").attr("src", json.data);
                    $.cookie("avatar", json.data, {"expires": 7});
                    alert("上传头像成功！");
                    console.log(json.data);
                    $("#img-avatar").attr("src", json.data);
                    $.cookie("avatar", json.data, {"expires": 7});
                } else {
                    alert("修改失败！" + json.message + "！");
                }
            }
        });

});