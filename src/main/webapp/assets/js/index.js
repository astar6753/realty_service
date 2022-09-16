$(function(){
    let map = null;
    let latlng = {"let":35.868293,"lng":128.594018};
    let markers = new Array();
    let markerPosition = [{
        "title":"마커",
        "letlng":new window.kakao.map.LatLng(35.868293,128.594018)
    }]
    if(!window.kakao || !window.kakao.maps) {
        const script = document.createElement("script");
        script.src="//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=9ed067574d7741f29d16b2c4443d3b92";
        script.addEventListener("load",() => {
            window.kakao.maps.load(() => {
                const container = document.getElementById("map");
                const option = {
                    center:new window.kakao.maps.LatLng(latlng.let, latlng.lng, 16),
                    level:3
                }
                map = new window.kakao.maps.Map(container, option);
                window.kakao.maps.event.addListener(map, 'draged', () => {
                    let center = map.getCenter();
                    latlng.lat = center.getLat();
                    latlng.lng = center.getLng();
                    console.log(latlng);
                })
                //마커 제거
                if(markers.length>0){
                    markers.forEach((marker) => {
                        marker.setMap(null);
                    })
                }
                markerPositions.push({
                    "title":"마커",
                    "letlng":new window.kakao.map.LatLng(35.868293,128.594018)
                })
                //마커 추가
                markerPositions.forEach((mark) => {
                    const marker = new window.kakao.maps.Marker({
                        map:map, position:mark.latlng, title:mark.title
                    })
                    markers.push(marker);
                });
            })
        })
        document.head.appendChild(script);
    }

    let realty_list;
    $.ajax({
        type:"post",
        url:"/api/realty/post/list",
        contentType:"application/json",
        data:JSON.stringify({"lat":35.868293, "lon":128.594018}),
        success:function(r) {
            console.log(r.list);
            console.log(r.message);
        },
        error:function(e) {
            console.log(e.reponseJSON.message);
        }
    })
})