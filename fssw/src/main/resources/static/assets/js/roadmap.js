$(".roadmapTitle").on("click", function(){
    // console.log("hi");
    // console.log($(this));
    // console.log($(this).next().attr("class"));
    $(this).next().slideToggle("slow");
    // $(this).css("display", "none");
    // $(this).next().css("display", "flex");
});
// $(".roadmapContent").on("click", function(){
//     console.log("hi");
//     console.log($(this));
//     console.log($(this).next().attr("id"));
//     $(this).css("display", "none");
//     $(this).prev().css("display", "flex");
// });

// function onClickRoadMap(e){
//     console.log(e.id);
//     console.log("\""+e.id+"\"");
//     console.log(e.nextElementSibling.id);
//     if (e.id==='roadmapTitle') {
//         console.log('ok1');
//         var ele=document.getElementById(e.id);
//         ele.style.display = "none !important;";
//         document.getElementById(e.nextElementSibling.id).style.display = 'flex !important;';
//         console.log('bye1');
//     } else {
//         console.log('ok2');
//         document.getElementById(e.nextElementSibling.id).style.display = 'none !important';
//         document.getElementById(e.id).style.display = 'flex !important';
//         console.log('bye2');
//     }
//     //if ($(this).css("display"))
//     //e.style.display = 'none';
//     //console.log(e.style.display);
//     //if ()
//     //document.getElementById(id).style.display = 'none';
// }