var ul = document.querySelector(".visual_img");

window.onload=function(){
    var firstItemClone = ul.firstElementChild.cloneNode(true);
    this.ul.appendChild(firstItemClone);
    var imgCnt = ul.childElementCount;
    this.ul.style.width = (ul.offsetWidth * imgCnt); //갯수만큼 옆으로 width 늘린다
    
    slideShow(imgCnt);
};

function slideShow(imgCnt){
    ul.style.left="0px";
    var count = 1;

    setInterval(() => {
        ul.style.transition = "transform 0.5s ease-in 0.4s";
        ul.style.transform = "translateX(-"+ul.offsetWidth*count+"px)";
        count+=1;

       if(count===imgCnt+1){
           //처음으로
           ul.style.transition='none';
           ul.style.transform = 'translateX(0px)';
            count=1;
       }
    }, 2000);
};