
/*-------프로모션 배너 슬라이딩---------*/
var ul = document.querySelector(".visual_img");
var interval;
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

    interval = setInterval(() => {
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
    return interval;
};
/*-------더보기 버튼 동작---------*/
var more_btn = document.querySelector(".btn");
var start = 4;
var categoryId=0;
more_btn.addEventListener('click',function(){
	start+=4;
	sendAjax("moreItem?start="+start+"&category_id="+categoryId,1);
	}
);
function replaceProduct(product_html,product){
	var resultHtml = product_html.replace("{id}",product.id)
	.replace("{placeName}",product.placeName)
.replace("{description}",product.description)
.replace("{description}",product.description)
	.replace("{imageUrl}",product.productImageUrl)
	.replace("{content}",product.content);
	return resultHtml
}

 function makeTemplate(data){
    var left = document.querySelector(".wrap_event_box").firstElementChild;
    var right = left.nextElementSibling;
    var product_html = document.querySelector("#itemList").innerHTML;
		for(var i=0;i<data.length;i++){
			
			var product = data[i];
			var resultHtml = replaceProduct(product_html,product);
			
	    	if(i%2==0)
	    		left.innerHTML += resultHtml;
	    	else
	    		right.innerHTML += resultHtml;
	    	
	    	if(product.id == "${count}") more_btn.style.display="none";
		}

};
/*---------------------ajax-----------------*/

function sendAjax(url,type){
	var request = new XMLHttpRequest();
	request.addEventListener("load", function(){
		var data = JSON.parse(request.responseText);
		if(type==1) //더보기
			return makeTemplate(data);
		else if(type==2) //tab전환
			return showCategoryItems(data);
		else if(type==3)//count 바꾸기
			return setNewCount(data);
		else if(type==4) //promotion바꾸기
			return setPromotions(data);
	})
	request.open("GET",url);
	request.send();
} 
/*-------탭 이동에 따른 프로모션 변화--------*/
function setPromotions(data){
	clearInterval(interval);
	var promotionHtml = document.querySelector("#promotionItem").innerHTML;
	var ul = document.querySelector(".visual_img");
	for(var i=0;i<data.length;i++){
	var resultHtml = promotionHtml.replace("{productImageUrl}",data[i].productImageUrl);

		if(i==0) ul.innerHTML = resultHtml;
		else ul.innerHTML +=resultHtml;
	}
	
	if(data.length>1){
		var firstItemClone = ul.firstElementChild.cloneNode(true);
	    this.ul.appendChild(firstItemClone);
	    var imgCnt = data.length+1;
	    this.ul.style.width = (ul.offsetWidth * imgCnt); //갯수만큼 옆으로 width 늘린다
	    
	    slideShow(imgCnt);
	    
		}
		else{
			ul.style.left="0px";
			ul.style.transition="none";
			ul.style.transform="translateX(0px)";
			
		}
}




/*-------탭 메뉴 동작 ---------*/

var category_tab = document.querySelector("#menutab_ul");

category_tab.addEventListener('click',function(evt){
	start=0;
   getCategoryId(evt);
   sendAjax("categoryItem?category_id="+categoryId,2);
	sendAjax("resetCount?category_id="+categoryId,3);
	sendAjax("promotionItem?category_id="+categoryId,4);
})

function setNewCount(newCount){
	document.querySelector(".pink").innerText = newCount+"개";
}

function getCategoryId(evt){
	var temp = evt.target.parentNode;
    if(temp.tagName!="LI") {
    	categoryId = temp.parentNode.getAttribute('data-category');
    }
    else categoryId = temp.getAttribute('data-category');
}

function showCategoryItems(data){
	 var left = document.querySelector(".wrap_event_box").firstElementChild;
	   var right = left.nextElementSibling;
	   var product_html = document.querySelector("#itemList").innerHTML;
	   var cid;
		for(var i=0;i<data.length;i++){
				var product = data[i];
				var resultHtml = replaceProduct(product_html,product);
		    	if(i==0){
		    		left.innerHTML = resultHtml;
		    		cid = product.categoryId;
		    	}
		    	else if(i==1)
		    		right.innerHTML = resultHtml;
		    	else if (i==2)
		    		left.innerHTML += resultHtml;
		    	else
		    		right.innerHTML+=resultHtml;
			}
		

}
