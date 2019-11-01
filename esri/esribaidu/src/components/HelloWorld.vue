<template>
  <div class="hello">
    <div id="map" style="width:100%; height:800px;"></div>
  </div>
</template>

<script>
import { loadModules } from 'esri-loader';
export default {
  name: 'HelloWorld',
  data () {
    return {
        map:null
    }
  },
  methods:{
    mapInit(){
      //地图初始化
        loadModules([
          'esri/Map',
          "esri/views/MapView",
          "esri/geometry/Extent",
          'esri/layers/BaseTileLayer',
          'esri/request',
          "esri/core/JSONSupport",
          'dojo/domReady!'])
        .then(([Map,MapView,Extent,BaseTileLayer,esriRequest,JSONSupport]) => {
            let BaiduLayer = BaseTileLayer.createSubclass([JSONSupport],{
                properties: {
                    urlPath: {
                        dependsOn: ["urlTemplate"], get: function () {
                            if (!this.urlTemplate) return null;
                            var b = this.urlTemplate
                            return b;
                        }
                    },
                    urlTemplate: null
                },
                getTileUrl: function(level, row, col) {
                    var zoom = level - 1;
                    var offsetX = Math.pow(2, zoom);
                    var offsetY = offsetX - 1;
                    var numX = col - offsetX;
                    var numY = (-row) + offsetY;
                    zoom = level + 1;
                    var url = this.urlPath;
                    url = url.replace(/\{numX\}/gi, numX).replace(/\{numY\}/gi, numY).replace(/\{zoom\}/gi, zoom);
                    // var url = "http://online1.map.bdimg.com/tile/?qt=tile&x=" + numX + "&y=" + numY + "&z=" + zoom + "&styles=pl";
                    return url;
                },
                fetchTile: function(level, row, col) {
                    var url = this.getTileUrl(level, row, col);
                    return esriRequest(url, {
                        responseType: "image"
                    })
                    .then(function(response) {

                      var image = response.data;
                      var width = this.tileInfo.size[0];
                      var height = this.tileInfo.size[0];

                      var canvas = document.createElement("canvas");
                      var context = canvas.getContext("2d");
                      canvas.width = width;
                      canvas.height = height;

                      context.drawImage(image, 0, 0, width, height);

                      return canvas;
                    }.bind(this));
                }
            });
            
            var options = {
                "id": "baidulayer",
                "label": "百度地图",
                "visible": true,
                "opacity": 1,
                "style": "default",
                "urlTemplate": "http://online1.map.bdimg.com/tile/?qt=tile&x={numX}&y={numY}&z={zoom}&styles=pl"
            };
            let layter = new BaiduLayer(options);
            // 地图初始范围
				    var initialExtent = new Extent(103.60, 24.62, 109.59, 29.22, null);
            var map = new Map({
              extent: initialExtent,
              layers: [layter]
            });

            var view = new MapView({
              container: "map",
              map: map,
              extent: initialExtent
            });

        })
        .catch(err => {
          console.error(err);
        });
    }
  },
  created(){
    this.mapInit();
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
