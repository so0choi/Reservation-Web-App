
/*-------프로모션 배너 슬라이딩---------*/
var interval;
window.onload=function(){
	var ul = document.querySelector(".visual_img");
    var firstItemClone = ul.firstElementChild.cloneNode(true);
    ul.appendChild(firstItemClone);
    var imgCnt = ul.childElementCount;
    ul.style.width = (ul.offsetWidth * imgCnt); //갯수만큼 옆으로 width 늘린다
    
    slideShow(imgCnt);
};

function slideShow(imgCnt){
	var ul = document.querySelector(".visual_img");
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

var categoryId=0;
	var start = 0;
more_btn.addEventListener('click',function(){
	start+=4;
	sendAjax("moreItem?start="+start+"&category_id="+categoryId,1);
	sendAjax("moreItem?start="+(start+4)+"&category_id="+categoryId,5);
	}
);

function replaceProduct(product_html,product){
	var resultHtml = product_html.replace("{id}",product.id)
	.replace("{placeName}",product.placeName)
.replace("{description}",product.productDescription)
.replace("{description}",product.productDescription)
	.replace("{imageUrl}",product.productImageUrl)
	.replace("{content}",product.productContent);
	return resultHtml
}

/*------------더보기 버튼 동작 아이템 추가--------------------------*/
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
		}
};

function checkIfMoreItem(data){
	if(data.length===0){
		document.querySelector(".btn").style.display='none';
	}
}

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
		else if(type==5)
			return checkIfMoreItem(data);
	})
	request.open("GET",url);
	request.send();
} 
/*-------탭 이동에 따른 프로모션 변화--------*/
function setPromotions(data){
	document.querySelector(".btn").style.display='block';
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
	    ul.appendChild(firstItemClone);
	    var imgCnt = data.length+1;
	    ul.style.width = (ul.offsetWidth * imgCnt); //갯수만큼 옆으로 width 늘린다
	    slideShow(imgCnt);
		}
		else{
			ul.style.transition="none";
			ul.style.transform="translateX(0px)";
			
		}
}

/*-------탭 메뉴 동작 ---------*/

var category_tab = document.querySelector("#menutab_ul");
category_tab.addEventListener('click',function(evt){
	if(getCategoryId(evt)){
		 var menu = category_tab.children;
		 //이전 메뉴 활성화 삭제
		 menu[0].firstElementChild.className="anchor"
		 for(var i=0;i<menu.length;i++){
			   if(menu[i].getAttribute('data-category')===categoryId){
				   menu[i].firstElementChild.className="anchor";
				   break;
			   }
		   }
	   categoryId = getCategoryId(evt); //클릭된 카테고리id로 바꾸기
	   //클릭된 메뉴 활성화
	   for(var i=0;i<menu.length;i++){
		   if(menu[i].getAttribute('data-category')===categoryId){
			   menu[i].firstElementChild.className="anchor active";
			   break;
		   }
	   }
	   start=0;
	   sendAjax("categoryItem?category_id="+categoryId,2);
		sendAjax("resetCount?category_id="+categoryId,3);
		sendAjax("promotionItem?category_id="+categoryId,4);
	}
   
})
/*-----------------선택한 카테고리 id 받아오기--------------*/
function getCategoryId(evt){
	var categoryIdTemp;
	var temp = evt.target.parentNode;
    if(temp.tagName!="LI") {
    	categoryIdTemp = temp.parentNode.getAttribute('data-category');
    }
    else categoryIdTemp = temp.getAttribute('data-category');
    return categoryIdTemp;
}
/*-----------------선택한 카테고리 공연 개수--------------*/
function setNewCount(newCount){
	document.querySelector(".pink").innerText = newCount+"개";
}
/*-----------------선택한 카테고리 아이템 출력--------------*/
function showCategoryItems(data){
	 var left = document.querySelector(".wrap_event_box").firstElementChild;
	   var right = left.nextElementSibling;
	   var product_html = document.querySelector("#itemList").innerHTML;
	   left.innerHTML = "";
	   right.innerHTML="";
		for(var i=0;i<data.length;i++){
				var product = data[i];
				var resultHtml = replaceProduct(product_html,product);
		    	if(i%2==0)
		    		left.innerHTML += resultHtml;
		    	else
		    		right.innerHTML += resultHtml;
		}
}
