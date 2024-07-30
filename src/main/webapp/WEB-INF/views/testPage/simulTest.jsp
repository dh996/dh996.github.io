<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<title>시뮬레이션 페이지</title>
<style>

.flex{
    display:flex;
}
* {
    cursor: default;
}
a {
    color: inherit; /* 링크의 색을 부모 요소에서 상속 */
    text-decoration: none; /* 링크의 밑줄 제거 */
    cursor: pointer;
}
#background{
    width: 100%;
    background-size: cover; /* 이미지가 요소에 맞게 확장되도록 설정합니다. */
    background-position: center; /* 이미지를 가운데 정렬합니다. */
    background-repeat: no-repeat; /* 이미지 반복을 비활성화합니다. */
    transition: background-image 1s ease-in-out;
}
#ui{
    max-width: 1000px;
    margin-left: auto; /* 요소를 가운데 정렬하기 위해 왼쪽 마진을 자동으로 설정합니다. */
    margin-right: auto;
    padding: 0 50px;
    background-color: rgba(255, 255, 255, 0.5);
}
#tale{
    height: 170px;
}
#simulMainBanner{
    justify-content: flex-start;
    height: 170px;
}
#menuBar{
    padding:10px;
    width: 145px;
    font-size: 16px;
    font-weight: bold;
    text-overflow: ellipsis;
}
#menuBar > div{
    margin-bottom: 8px;
    justify-content: space-between;
}
#start{
    width: 145px;
    height: 30px;
    border-radius: 10px;
    text-align: center;
    line-height: 30px;
    color: #fff;
    background: #555;
    transition: background-color 0.3s;
}
#start > a{
    display: inline-block; /* 링크를 인라인 블록 요소로 변경하여 너비와 높이를 설정할 수 있도록 함 */
    width: 100%; /* 부모 요소의 너비를 전체로 확장 */
    height: 100%; /* 부모 요소의 높이를 전체로 확장 */
    text-decoration: none; /* 링크 텍스트의 밑줄 제거 */
    color: #fff;
}
#start:hover{
    cursor: pointer;
    background-color: #888;
    color: #fff;
}
#start.on:hover{
    background: #ff0000;
}
#pickChamps{
    height: 155px;
}
#champList {
    height:600px;
    overflow: auto;
}
#champListBox {
    flex-wrap: wrap;
    justify-content: flex-start;
    padding: 3px;
}

.champ {
    margin: 7px; /* 플렉스 아이템 간격 조절 */
    width: 150px; /* 가로 크기 고정 */
    height: 150px; /* 세로 크기 고정 */
    background: #555;
    transition: background-color 0.3s; /* 변화를 0.3초 동안 부드럽게 만듦 */
}
.champ:hover{
    background-color: #888; /* 호버 시 배경색 변화 */
}
.champ:hover, /* 부모 요소 호버 효과 */
.champ:hover > * { /* 자식 요소 호버 효과 */
    cursor: pointer;
}

/* champ 클래스 내부의 이미지 스타일 */
.champ img {
    max-width: 100%; /* 가로 크기 100%로 설정하여 부모 요소에 맞춤 */
    height: auto; /* 세로 크기는 자동 조정 */
}
.tagName img {
    height: 20px; /* 세로 크기는 자동 조정 */
}

/* champ 클래스 내부의 name 클래스의 스타일 */
.champ .name {
    padding: 2px; /* 패딩 설정 */
    overflow: hidden; /* 글자가 div 밖으로 벗어날 경우 숨김 */
    white-space: nowrap; /* 글자가 div 안에서 줄바꿈되지 않도록 설정 */
    text-overflow: ellipsis; /* 글자가 div를 벗어날 경우 ...으로 표시 */
    color: #fff;
    font-size: 20px;
    font-weight: bold;
}
.cid {
    display: none;
}
.champ.on{
    background: #ff0000;
}
.tagName {
    padding: 3px;
}
/* 스크롤바의 전체 스타일 */
::-webkit-scrollbar {
    width: 8px; /* 스크롤바의 너비 */
}

/* 스크롤바의 추적(트랙) 스타일 */
::-webkit-scrollbar-track {
    background-color: rgba(255, 255, 255, 0.5); /* 추적(트랙)의 배경색 */
}

/* 스크롤바의 슬라이더(쓰롯) 스타일 */
::-webkit-scrollbar-thumb {
    background-color: #888; /* 슬라이더(쓰롯)의 배경색 */
    border-radius: 3px; /* 슬라이더(쓰롯)의 둥근 모서리 */
}

/* 마우스 오버시 스크롤바의 슬라이더(쓰롯) 스타일 */
::-webkit-scrollbar-thumb:hover {
    background-color: #555; /* 마우스 오버시 슬라이더(쓰롯)의 배경색 */
}
</style>
</head>
<body>
<article id="background" class="background1">
<div id="ui">
    <div id="simulMainBanner" class="flex">
        <div id="menuBar">
            <div class="flex"><div>VERSION</div><div>${version}</div></div>
            <div class="flex"><div>PLAYER</div><div>${id}</div></div>
            <div>PICK 5 CHAMP</div>
            <div id="start" class="ready">시작하기</div>
            <div><a href="/packs">메뉴로</a></div>
        </div>
        <div id="pickChamps" class="flex">
        </div>
    </div>
    <div id="champList">
    <div id="champListBox" class="flex">
        <c:forEach var="champion" items="${champDTOList}">
        <div class="champ ${champion.champName.replaceAll(' ', '')}">
            <div class="name">${champion.champName}</div>
            <div class="cid">${champion.champID}</div>
            <div class="tags flex">
                <c:forEach var="tags" items="${champion.champTags}">
                <div class="tagName">
            <div class="name">${tags}</div>
                </div>
                </c:forEach>
            </div>
        </div>
        </c:forEach>
    </div>
    </div>
    <div id="tale">
    </div>
</div>
</article>
    
    <script>
        var jQ = jQuery.noConflict();
        
        var id = '${id}'
        console.log(id)
        
        var selectCount = 0;
        
        jQ('#champListBox > .champ').click(function(){
        	if(jQ(this).hasClass('on')){
        		jQ(this).removeClass('on');
        		selectCount --;
        	}else{
        		if(selectCount<5){
        			jQ(this).addClass('on');
        			selectCount ++;
        		}
        	}
        	selectChampSetting();
        })
        
        function selectChampSetting(){
        	// on 클래스를 가지고 있는 .champ 요소들을 선택
            var selectedChamps = jQ('.champ.on');

            // 기존에 있던 #pickChamps의 자식들을 제거
            jQ('#pickChamps').empty();

            // 각 선택된 .champ 요소를 복제하여 새로운 .champ를 생성하고, #pickChamps의 자식으로 추가
            selectedChamps.each(function () {
                var clonedChamp = jQ(this).clone();
                var keyWord = jQ(this).find('.name').text().replaceAll(' ', '');
                console.log(keyWord);
                clonedChamp.removeClass('on'); // 'on' 클래스 제거
                clonedChamp.on('click', function () {
                    removeChampList(keyWord);
                    jQ(this).remove(); // 클릭된 div를 제거
                });
                jQ('#pickChamps').append(clonedChamp);
            });
        }
        
        function removeChampList(keyWord){
        	console.log(keyWord);
        	var target = document.querySelectorAll('#champListBox .champ.' + keyWord + '.on');
            if(target.length > 0){ // 선택된 요소가 있는지 확인
                target.forEach(function(element) {
                	console.log("removeChampList");
                    element.classList.remove('on');
                });
                selectCount--;
            }
        }
        
        
        function startAjax() {
            var selectedChampData = [];

            jQ('.champ.on').each(function () {
                var champName = jQ(this).find('.name').text();
                var champId = jQ(this).find('.cid').text();

                selectedChampData.push({
                    name: champName,
                    id: champId
                });
            });

            // Ajax 요청
            jQ.ajax({
                type: 'POST',
                url: '/kr/simul/simul',  // 실제 엔드포인트에 맞게 변경
                contentType: 'application/json',
                data: JSON.stringify({
                    version: '${version}',
                    selectedChamps: selectedChampData,
                    userID: id
                }),
                success: function (response) {
                    // 성공 시 동작
                    //console.log('Ajax request succeeded:', response);
                    console.log(response);
                    createResultBanner(response)
                    //simulResult(response, '${id}');
                },
                error: function (error) {
                    // 실패 시 동작
                    console.error('Ajax request failed:', error);
                }
            });
        }
        
        function createResultBanner(tempId){
        	var resultLink = '<a href="//simul/'+sid+'">결과확인</a>'
        	jQ('#start').empty()
        	jQ('#start').addClass('on');
        	jQ('#start').append(resultLink)
        }
        
        /* window.addEventListener('beforeunload', function (event) {
            // Ajax 요청 보내기
            $.ajax({
                type: 'POST',
                url: '/tempReset/'+${id},  // 로그아웃을 처리하는 엔드포인트 URL
                async: false,   // 동기적으로 요청을 처리하려면 async 옵션을 false로 설정
                success: function (response) {
                    // 서버로부터 성공적인 응답을 받았을 때 수행할 작업
                    console.log('tempReset successful');
                },
                error: function (xhr, status, error) {
                    // 서버 요청이 실패했을 때 수행할 작업
                    console.error('tempReset failed:', error);
                }
            });
        }); */
        
        /* function simulResult(tempID, userID){
        	jQ.ajax({
                type: 'POST',
                url: '/packs/simul/simulResult',  // 실제 엔드포인트에 맞게 변경
                contentType: 'application/json',
                data: JSON.stringify({
                	tempID: tempID,
                    userID: userID
                }),
                success: function (response) {
                    // 성공 시 동작
                    console.log('Ajax request succeeded:', response);
                    
                },
                error: function (error) {
                    // 실패 시 동작
                    console.error('Ajax request failed:', error);
                }
            });
        } */
        
        jQ("#start").click(function(){
        	if(selectCount==5 && jQ("#start").hasClass("ready")){
        		startAjax();
        		jQ("#start").removeClass("ready");
        	}else if(selectCount<5 && jQ("#start").hasClass("ready")){
        		alert("5개를 골라주세요");
        	}
        })
    </script>
</body>
</html>