$(".curCate").on("click", function(){
    $(".curCate").css("background", "#fff");
    $(".curCate").css("color", "#000");
    $(".curContents").css("display", "none");
    $(this).css("background", "#2071fa");
    $(this).css("color", "#fff");
    $("#" + $(this).attr("role")).css("display", "block");
});