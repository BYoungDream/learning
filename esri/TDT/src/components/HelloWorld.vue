<template>
  <div>
    <div id="mapDiv" style="width:100%; height:500px; border:solid 1px red;"></div>
    <div>
        <input type="button" value="打点" @click="pointClick" />
        <input type="button" value="隐藏" @click="pointShowOrHide('testpoint',false)" />
        <input type="button" value="显示" @click="pointShowOrHide('testpoint',true)" />
        <input type="button" value="矢量图" @click="tdtxlClick" />
        <input type="button" value="影像图" @click="tdtyxClick" />
        <input type="button" value="画线" @click="hxClick" />
        <input type="button" value="清除线" @click="clenxClick" />
        <input type="button" value="弹窗" @click="popupClick" />
        <input type="button" value="画面" @click="polygonClick">
        <input type="button" value="删除面" @click="deletePolygon">
        <child v-on:listenhandleChange="childChange"></child>
    </div>
    <div class="el_zoom">
      <table cellpadding="0" cellspacing="0" border="0">
        <tbody>
          <tr>
            <td>
              <img src="../assets/img/NavDishLeftTop.png">
            </td>
            <td>
              <img class="el-slider-imgbtn" id="imgPanUp" @click="imgPanUpClick" src="../assets/img/NavDishTop.png">
            </td>
            <td>
              <img src="../assets/img/NavDishRightTop.png">
            </td>
            </tr>
            <tr>
              <td>
                <img class="el-slider-imgbtn" id="imgPanLeft" @click="imgPanLeftClick" src="../assets/img/NavDishLeft.png"></td><td><img class="el-slider-imgbtn" id="imgPanHome" @click="panHomeClick" src="../assets/img/NavDishCenter.png"></td><td><img class="el-slider-imgbtn" id="imgPanRight" @click="imgPanRightClick" src="../assets/img/NavDishRight.png"></td></tr><tr><td><img src="../assets/img/NavDishLeftBottom.png"></td><td><img class="el-slider-imgbtn" id="imgPanDown" @click="imgPanDownClick" src="../assets/img/NavDishBottom.png"></td><td><img src="../assets/img/NavDishRightBottom.png" style="margin-left: 0">
              </td>
            </tr>
        </tbody>
        </table>
        <img id="imgZoomUp" @click="imgZoomUpClick" class="el-slider-imgbtn el-slider-endpoint" src="../assets/img/ZoomBarTop.png">
        <div class="block" style="margin-left:4px; margin-bottom:6px;">
          <el-slider
            v-model="zoomValue"
            vertical
            :show-tooltip="false"
            :min="2"
            :max="17"
            height="130px">
          </el-slider>
        </div>
        <img id="imgZoomDown" @click="imgZoomDownClick" class="el-slider-imgbtn el-slider-endpoint" src="../assets/img/ZoomBarBottom.png">
    </div>
    
  </div>
</template>

<script type="text/javascript">
import { loadModules } from 'esri-loader';
import mapConfig from './mapConfig.js';
import layerProv from './layerProv.json';
import layerCounty from './layerCounty';
import layerPolygon from './layerPolygon.json';
import child from './Child';
import 'jquery';
export default {
  name: 'HelloWorld',
  data () {
    return {
      map: '',
      mapView: '',
      mapConfig:mapConfig,
      layerProv:layerProv,
      layerCounty:layerCounty,
      layerPolygon:layerPolygon,
      zoomValue:8,
      timer: ''
    }
  },
  components:{
    child
  },
  methods:{
    mapInit(){
      //地图初始化
      var _this = this;
      loadModules([
        "dojo/dom",
        "dojo/on",
        "dojo/_base/declare",
        "dojo/json",
        "dojo/_base/lang",
        "dojo/_base/url",
        "dojo/string",
        "dojo/dom-construct",
        "dojo/number",
        "esri/Map",
        "esri/geometry/Extent",
        "esri/layers/support/TileInfo",
        "esri/layers/support/LOD",
        "esri/geometry/SpatialReference",
        "esri/geometry/Point",
        "esri/views/MapView",
        "esri/views/SceneView",
        "esri/layers/WebTileLayer",
        "esri/layers/TileLayer",
        "esri/layers/MapImageLayer",
        "esri/core/urlUtils",
        "esri/core/JSONSupport",
        "esri/widgets/Zoom",
        "esri/widgets/Popup",
        "dojo/domReady!"
      ])
      .then(([
        dom, on, declare, JSON, lang,url,string, domConstruct, number,
        Map,Extent,TileInfo,LOD,SpatialReference,Point,
        MapView,SceneView,WebTileLayer,TileLayer,MapImageLayer,urlUtils,JSONSupport,
        Zoom,Popup
      ]) => {
        
        // 地图初始范围
        var initialExtent = new Extent(104.361, 20.693, 112.1873, 26.5223, null);

        //创建天地图图层
        var  TiandituWMTSLayer = WebTileLayer.createSubclass([JSONSupport], {
            declaredClass: "TiandituWMTSLayer",
            normalizeCtorArgs: function (b, c) {
                return "string" === typeof b ? lang.mixin({urlTemplate: b}, c || {}) : b
            },
            getDefaults: function (b) {
                var sp = new SpatialReference({
                    "wkid": 4326
                });
                var c = new Extent(-180, -90, 180, 90, sp);
                return lang.mixin({
                    fullExtent: c,
                    tileInfo: new TileInfo({
                        rows: 256,
                        cols: 256,
                        dpi: 90.71428571428571,
                        format: "PNG8",
                        compressionQuality: 0,
                        origin: new Point({x: -180, y: 90, spatialReference: sp}),
                        spatialReference: sp,
                        lods: [
                            {"level" : 1,"levelValue":1, "resolution" : 0.7039130078555585, "scale" : 2.95828763796E8},
                            {"level" : 2,"levelValue":2, "resolution" : 0.35156249999999994, "scale" : 1.47914381898E8},
                            {"level" : 3,"levelValue":3, "resolution" : 0.17578124999999997, "scale" : 7.39571909489E7},
                            {"level" : 4,"levelValue":4, "resolution" : 0.08789062500000014, "scale" : 3.69785954745E7},
                            {"level" : 5,"levelValue":5, "resolution" : 0.04394531250000007, "scale" : 1.84892977372E7},
                            {"level" : 6,"levelValue":6, "resolution" : 0.021972656250000007, "scale" : 9244648.86862},
                            {"level" : 7,"levelValue":7, "resolution" : 0.01098632812500002, "scale" : 4622324.43431},
                            {"level" : 8,"levelValue":8, "resolution" : 0.00549316406250001, "scale" : 2311162.21716},
                            {"level" : 9,"levelValue":9, "resolution" : 0.0027465820312500017, "scale" : 1155581.10858},
                            {"level" : 10,"levelValue":10, "resolution" : 0.0013732910156250009, "scale" : 577790.554289},
                            {"level" : 11,"levelValue":11, "resolution" : 0.000686645507812499, "scale" : 288895.277144},
                            {"level" : 12,"levelValue":12, "resolution" : 0.0003433227539062495, "scale" : 144447.638572},
                            {"level" : 13,"levelValue":13, "resolution" : 0.00017166137695312503, "scale" : 72223.819286},
                            {"level" : 14,"levelValue":14, "resolution" : 0.00008583068847656251, "scale" : 36111.909643},
                            {"level" : 15,"levelValue":15, "resolution" : 0.000042915344238281406, "scale" : 18055.954822},
                            {"level" : 16,"levelValue":16, "resolution" : 0.000021457672119140645, "scale" : 9027.977411},
                            {"level" : 17,"levelValue":17, "resolution" : 0.000010728836059570307, "scale" : 4513.9887055},
                            {"level" : 18,"levelValue":18, "resolution" : 0.000005364418029785169, "scale" : 2256.99435275}
                        ]
                    })
                })
            },
            properties: {
                copyright: "",
                legendEnabled: {
                    json: {
                        readFrom: ["showLegend"], read: function (b, c) {
                            return null != c.showLegend ? c.showLegend : !0
                        }
                    }
                },
                levelValues: {
                    dependsOn: ["tileInfo"], get: function () {
                        var b = [];
                        if (!this.tileInfo) return null;
                        this.tileInfo.lods.forEach(function (c) {
                            b[c.level] = c.levelValue || c.level
                        }, this);
                        return b
                    }
                },
                popupEnabled: {
                    json: {
                        readFrom: ["disablePopup"], read: function (b, c) {
                            return null != c.disablePopup ? !c.disablePopup : !0
                        }
                    }
                },
                spatialReference: new SpatialReference({"wkid": 4326}),
                subDomains: null,
                tileServers: {
                    value: null,
                    dependsOn: ["urlTemplate", "subDomains", "urlPath"],

                    get: function () {
                        var b = new url(this.urlTemplate),
                            c = b.scheme ? b.scheme + "://" : "//",
                            a = c + b.authority + "/",
                            e = this.subDomains,
                            d, f = [];
                        -1 === b.authority.indexOf("{subDomain}") && f.push(a);
                        // e && (0 < e.length && 1 < b.authority.split(".").length) && e.forEach(function (a, e) {
                        //     -1 < b.authority.indexOf("{subDomain}") && (d = c + b.authority.replace(/\{subDomain\}/gi, a) + "/");
                        //     f.push(d)
                        // }, this);
                        // return f = f.map(function (b) {
                        //     "/" !== b.charAt(b.length - 1) && (b +=
                        //         "/");
                        //     return b
                        // })

                        return f;
                    }
                },
                urlPath: {
                    dependsOn: ["urlTemplate"], get: function () {
                        if (!this.urlTemplate) return null;
                        var b = this.urlTemplate,
                            a = new url(b);
                        return b.substring(((a.scheme ? a.scheme + "://" : "//") + a.authority + "/").length)
                    }
                },
                urlTemplate: null
            },
            getTileUrl: function (b, a, d) {
                b = this.levelValues[b];
                var e = this.tileServers[a % this.tileServers.length] + string.substitute(this.urlPath, {
                        level: b,
                        col: d,
                        row: a
                    }),
                    e = e.replace(/\{level\}/gi, b).replace(/\{row\}/gi, a).replace(/\{col\}/gi, d);
                return urlUtils.addProxy(e)
            }
        });

        var layersOpt=[];
        if(_this.mapConfig.BaseMapLayers != null && _this.mapConfig.BaseMapLayers.length > 0){
          _this.mapConfig.BaseMapLayers.forEach((item, index) => {
            var options = {
                  "id": item.id,
                  "label": item.title,
                  "visible": item.initVisible,
                  "opacity": item.opacity,
                  "style": "default",
                  "wmtslayer": "vec",
                  "tileMatrixSet": "c",
                  "format": "tiles",
                  "urlTemplate": item.restURL,
                  "copyright": "TiandituWMTS",
                  subDomains: ["www"]
              };

              var layer = new TiandituWMTSLayer(options);
              layersOpt.push(layer);

          });

        }

        //创建地图
        _this.map = new Map({
					extent: initialExtent,
          layers:layersOpt
        });

        //创建地图view
        _this.mapView = new MapView({
          container: "mapDiv",
          map: _this.map,
          extent: initialExtent
        });

        //设置边框
        _this.createLayerPolyline();


        // this.watchMapMouse();
        
        //弹出popup窗
        // _this.mapView.on("click", function(event){
        //   _this.mapView.popup.location = event.mapPoint;
        //   _this.mapView.popup.visible = true;
        // });

        
      })
      .catch(err => {
        console.error(err);
      });

    },
    createLayerPolyline(){
      //设置边框
      loadModules([
          "esri/Graphic",
          "esri/geometry/Polyline",
          "esri/symbols/SimpleLineSymbol",
          "esri/Color",
          "esri/symbols/TextSymbol",
          "esri/geometry/Point",
          "esri/layers/WebTileLayer",
          "esri/layers/GraphicsLayer",
          "dojo/domReady!"
        ])
        .then(([
          Graphic,
          Polyline,
          SimpleLineSymbol,
          Color,
          TextSymbol,
          Point,
          WebTileLayer,
          GraphicsLayer
        ]) => {
            let polyline, lineSymbol;

            polyline = new Polyline({
              type: "polyline",
              paths: this.layerProv,
              spatialReference:4326
            });
            lineSymbol = new SimpleLineSymbol({
              color: "#FF4500",
              width: 2
            });
            

            let polylineGraphic = new Graphic({
              geometry: polyline,
              symbol: lineSymbol,
              attributes: []
            });

            let layeritem = new GraphicsLayer({
                      id: "tdtbk",
                      title: "tdtbk",
                      copyright: ""
            });

            layeritem.add(polylineGraphic);

            this.map.add(layeritem);
          
        })
        .catch(err => {
          console.error(err);
        });

    },
    createLayerPointSymbolCustom(pointLayterStyle){
      //打点图标
      loadModules([
          "esri/layers/GraphicsLayer",
          "esri/Graphic",
          "esri/geometry/Point",
          "esri/symbols/TextSymbol",
          "esri/symbols/PictureMarkerSymbol",
          "esri/symbols/SimpleMarkerSymbol",
          "esri/Color",
          "esri/symbols/SimpleLineSymbol",
          "esri/PopupTemplate",
          "dojo/domReady!"
      ])
      .then(([
          GraphicsLayer,
          Graphic,
          Point,
          TextSymbol,
          PictureMarkerSymbol,
          SimpleMarkerSymbol,
          Color,
          SimpleLineSymbol,
          PopupTemplate
      ]) => {

          //判断图层是否存在
          let layer = this.map.findLayerById(pointLayterStyle.layerid);
          if (layer && layer.removeAll != undefined){
              layer.visible = true;
              return;
          }

          if(pointLayterStyle !=null){
              //颜色打点
              // let pointSymbol = new SimpleMarkerSymbol(
              //       "circle",
              //       12,
              //       new SimpleLineSymbol(
              //         "",
              //         "black",
              //         "4"
              //       ),
              //       "red"
              // );
              
              //图标打点
              let pointSymbol = new PictureMarkerSymbol({
                    url: pointLayterStyle.imgUrl,
                    width: pointLayterStyle.imgWidth,
                    height: pointLayterStyle.imgHeight,
                    xoffset: 0,
                    yoffset: 0
              });

              let layeritem = new GraphicsLayer({
                      id: pointLayterStyle.layerid,
                      title: pointLayterStyle.layertitle,
                      copyright: ""
              });

              for(let i=0; i < pointLayterStyle.arrayList.length; i++){
                    let point = new Point({
                    x: pointLayterStyle.arrayList[i][0],
                    y: pointLayterStyle.arrayList[i][1],
                    z: null
                    });

                    let pointGraphic = new Graphic({
                        geometry: point,
                        symbol: pointSymbol,
                        attributes: null
                    });

                    let txt = "<div class='sizer content' style='width: 180px;'>"
                            + "<div class='contentPane' style='max-height: 300px;'>"
                            + "<table class='infoWindowContent' width='100%' style='background:#FFF;'>"
                            + "<tbody>"
                            + "<tr>"
                            + "<td align='center' colspan='2'>太平镇</td>"        
                            + "</tr>"
                            + "<tr>"        
                            + "<td align='left'>测站编码:</td>"        
                            + "<td align='left'>8070235A</td>"          
                            + "</tr>"          
                            + "<tr>"        
                            + "<td align='left'>当前水位:</td><td align='left'>108米</td>"        
                            + "</tr>"          
                            + "<tr>"        
                            + "<td width='50%' align='left'>汛 限水位:</td><td align='left'>108米</td>"        
                            + "</tr>"          
                            + "<tr>"        
                            + "<td align='left'>超汛限水位:</td><td align='left'>0米</td>"        
                            + "</tr>"          
                            + "<tr>"        
                            + "<td align='left'>今日雨量:</td><td align='left'>0毫米</td>"        
                            + "</tr>"          
                            + "<tr>"        
                            + "<td align='left'>昨日降雨:</td><td align='left'>0毫米</td>"        
                            + "</tr>"          
                            + "</tbody>"        
                            + "</table>"        
                            + "</div>"
                            + "</div>";
                              
                            
                    var template = new PopupTemplate({
                      title:"左江",
                      content: txt,
                      visible: true
                    });

                    pointGraphic.popupTemplate = template;

                    layeritem.add(pointGraphic);
              }
              
              this.map.layers.add(layeritem);

          }
          
        
      })
      .catch(err => {
        console.error(err);
      });

    },
    createCustomPoint(x,y){
      //创建闪烁点，并几秒后消失
      loadModules([
          "esri/layers/GraphicsLayer",
          "esri/Graphic",
          "esri/geometry/Point",
          "esri/symbols/TextSymbol",
          "esri/symbols/PictureMarkerSymbol",
          "esri/symbols/SimpleMarkerSymbol",
          "esri/Color",
          "esri/symbols/SimpleLineSymbol",
          "esri/PopupTemplate",
          "dojo/domReady!"
      ])
      .then(([
          GraphicsLayer,
          Graphic,
          Point,
          TextSymbol,
          PictureMarkerSymbol,
          SimpleMarkerSymbol,
          Color,
          SimpleLineSymbol,
          PopupTemplate
      ]) => {

          if(x !=null && y != null){
              
              //图标打点
              let pointSymbol = new PictureMarkerSymbol({
                    url: require('../assets/img/ZoomTo.gif'),
                    width: 20,
                    height: 20,
                    xoffset: 0,
                    yoffset: 0
              });

              var layerid = "customPoint"+Math.ceil(Math.random()*10000);
              let layeritem = new GraphicsLayer({
                      id: layerid,
                      title: "闪烁",
                      copyright: ""
              });

              let point = new Point({
                  x: x,
                  y: y,
                  z: null
              });

              let pointGraphic = new Graphic({
                  geometry: point,
                  symbol: pointSymbol,
                  attributes: null
              });

              layeritem.add(pointGraphic);
              
              this.map.layers.add(layeritem);

              this.timer = setTimeout(this.closeCustomPoint, 4000,layerid);

          }
          
        
      })
      .catch(err => {
        console.error(err);
      });

    },
    createGraphicline(polylineData){
        //画线
        loadModules([
          "esri/Graphic",
          "esri/geometry/Polyline",
          "esri/symbols/SimpleLineSymbol",
          "esri/Color",
          "esri/symbols/TextSymbol",
          "esri/geometry/Point",
          "esri/layers/WebTileLayer",
          "esri/layers/GraphicsLayer",
          "dojo/domReady!"
        ])
        .then(([
          Graphic,
          Polyline,
          SimpleLineSymbol,
          Color,
          TextSymbol,
          Point,
          WebTileLayer,
          GraphicsLayer
        ]) => {
            let polyline, lineSymbol;

            polyline = new Polyline({
              type: "polyline",
              paths: polylineData,
              spatialReference:4326
            });
            lineSymbol = new SimpleLineSymbol({
              color: "#FF4500",
              width: 2
            });
            

            let polylineGraphic = new Graphic({
              geometry: polyline,
              symbol: lineSymbol,
              attributes: []
            });

            this.mapView.graphics.add(polylineGraphic);

            var geomePolygonSingle = polylineGraphic.geometry; //获取图层中的几何信息
            var extent = geomePolygonSingle.extent; //获取图形范围信息
            this.mapView.extent = extent;
          
        })
        .catch(err => {
          console.error(err);
        });

    },
    createGraphicPolygon(polygonStyle){
        //创建面图层
        loadModules([
          "esri/layers/GraphicsLayer",
          "esri/Graphic",
          "esri/geometry/Polygon",
          "esri/geometry/geometryEngine",
          "esri/symbols/SimpleFillSymbol",
          "esri/Color",
          "dojo/domReady!"
        ])
        .then(([
          GraphicsLayer,
          Graphic,
          Polygon,
          GeometryEngine,
          SimpleFillSymbol,
          Color
        ]) => {

            let fillSymbol = {
              type:"simple-fill",
              color: polygonStyle.bgColor,   //背景色
              outline: {
                color: polygonStyle.borderColor,   //边框色
                width: 2,    //边框宽度
                opacity: 0.7  //透明度
              }
            };

            let polygon = new Polygon({
                type: "polygon",
                rings: polygonStyle.rings,
                spatialReference: 4326
            });

            let polygonGraphic = new Graphic({
              geometry: polygon,
              symbol: fillSymbol,
              attributes: []
            });

            let layeritem = new GraphicsLayer({
                  id: polygonStyle.id,
                  title: polygonStyle.title,
                  copyright: ""
            });

            layeritem.add(polygonGraphic);
            this.map.layers.add(layeritem);
          
        })
        .catch(err => {
          console.error(err);
        });
    },
    pointClick(){
      //打点操作
      //图片打点
      // let arrPoint =[[110.755,25.5913],[105.4909,24.8186],[107.044,25.0271],[107.513,24.0408],[107.9798,23.7286]];
      let arrPoint =[[110.755,25.5913]];
      let pointLayterStyle = {
        layerid:"testpoint",
        layertitle:"打点",
        arrayList:arrPoint,
        imgUrl:require('../assets/img/alarm07.png'),
        imgWidth:14,
        imgHeight:14
      }
      this.createLayerPointSymbolCustom(pointLayterStyle);

    },
    pointShowOrHide(layterId,isVisible){
      //图层显示隐藏
      let layer = this.map.findLayerById(layterId);
      if (layer){
          layer.visible = isVisible;

      }
      
    },
    tdtxlClick(){
      //显示矢量地图
      this.pointShowOrHide("TiandituLayer_gx",true);
      this.pointShowOrHide("TiandituLayer_gxbz",true);
      this.pointShowOrHide("TiandituLayer_gxyx",false);
      this.pointShowOrHide("TiandituLayer_gxyxbz",false);

    },
    tdtyxClick(){
      //显示影像图
      this.pointShowOrHide("TiandituLayer_gx",false);
      this.pointShowOrHide("TiandituLayer_gxbz",false);
      this.pointShowOrHide("TiandituLayer_gxyx",true);
      this.pointShowOrHide("TiandituLayer_gxyxbz",true);

    },
    hxClick(){
      //画线事件,this.layerCounty是忻城县数据
      this.createGraphicline(this.layerCounty);
    },
    clenxClick(){
      //清除事件
      this.mapView.graphics.removeAll();
    },
    childChange(parm){
      console.log("子组件child传过来的方法:"+parm);
    },
    panHomeClick(){
      //地图复原操作
      loadModules([
          "esri/geometry/Extent",
          "esri/symbols/SimpleLineSymbol",
          "dojo/domReady!"
        ])
        .then(([
          Extent,
          SimpleLineSymbol
        ]) => {
            var initialExtent = new Extent(104.361, 20.693, 112.1873, 26.5223, null);
            this.mapView.extent = initialExtent;
            this.zoomValue = this.mapView.zoom;
        })
        .catch(err => {
          console.error(err);
        });
      
    },
    imgZoomUpClick(){
      //地图放大
      this.mapView.zoom++;
      this.zoomValue =this.mapView.zoom;
    },
    imgZoomDownClick(){
      //地图缩小
      this.mapView.zoom--;
      this.zoomValue = this.mapView.zoom;
    },
    imgPanUpClick(){
      //地图向上移动
      loadModules([
          "esri/geometry/Extent",
          "esri/symbols/SimpleLineSymbol",
          "dojo/domReady!"
        ])
        .then(([
          Extent,
          SimpleLineSymbol
        ]) => {
            var initialExtent = this.mapView.extent;
            initialExtent.ymin--;
            this.mapView.extent = initialExtent;
        })
        .catch(err => {
          console.error(err);
        });
    },
    imgPanLeftClick(){
      //地图向左移动
      loadModules([
          "esri/geometry/Extent",
          "esri/symbols/SimpleLineSymbol",
          "dojo/domReady!"
        ])
        .then(([
          Extent,
          SimpleLineSymbol
        ]) => {
            var initialExtent = this.mapView.extent;
            initialExtent.xmin++;
            this.mapView.extent = initialExtent;
        })
        .catch(err => {
          console.error(err);
        });
    },
    imgPanRightClick(){
      //地图向右移动
      loadModules([
          "esri/geometry/Extent",
          "esri/symbols/SimpleLineSymbol",
          "dojo/domReady!"
        ])
        .then(([
          Extent,
          SimpleLineSymbol
        ]) => {
            var initialExtent = this.mapView.extent;
            initialExtent.xmin--;
            this.mapView.extent = initialExtent;
        })
        .catch(err => {
          console.error(err);
        });
    },
    imgPanDownClick(){
      //地图向下移动
      loadModules([
          "esri/geometry/Extent",
          "esri/symbols/SimpleLineSymbol",
          "dojo/domReady!"
        ])
        .then(([
          Extent,
          SimpleLineSymbol
        ]) => {
            var initialExtent = this.mapView.extent;
            initialExtent.ymin++;
            this.mapView.extent = initialExtent;
        })
        .catch(err => {
          console.error(err);
        });
    },
    watchMapMouse(){
      //监视鼠标放大缩小地图事件
      let _this = this;
      loadModules([
          "esri/core/watchUtils",
          "dojo/domReady!"
        ])
        .then(([
          watchUtils
        ]) => {
            watchUtils.watch(_this.mainView, "zoom", function(){
              console.log("你好呀!");
              // this.zoomValue = newvalue;
            });

        })
        .catch(err => {
          console.error(err);
        });
    },
    popupClick(){
      //弹窗
      loadModules([
        "esri/geometry/Point",
        "dojo/domReady!"
      ])
      .then(([
        Point
      ]) => {
        
        let layer = this.map.findLayerById("testpoint");
        let point = new Point({
          x:110.755,
          y:25.5913
        });
        if(layer){
          this.mapView.popup.open({
            features: layer.graphics,
            location: point
          });
        }

        //闪烁点
        this.createCustomPoint(110.755,25.5913);
        
      })
      .catch(err => {
        console.error(err);
      });

    },
    closeCustomPoint(layerid){
      //关闭闪烁点
      let layer = this.map.findLayerById(layerid);
      if(layer){
        layer.removeAll();
        clearTimeout(this.timer);
      }

    },
    polygonClick(){
      //地图画面点击事件
      let polygonStyle={
        id:"polygonid",
        title:"polygonTitle",
        bgColor:"rgba(84,255,159,0.6)",
        // bgColor:"#54FF9F",
        borderColor:"#575757",
        rings:this.layerPolygon
      }
      this.createGraphicPolygon(polygonStyle);

    },
    deletePolygon(){
      //删除面
      let layer = this.map.findLayerById("polygonid");
      if (layer){
          layer.removeAll();
      }
    }

  },
  watch: {
      zoomValue(newValue,oldValue){
        if(newValue !== oldValue){
          this.mapView.zoom = newValue;
        }
      }
  },
  created(){
    this.mapInit();
  }

}
</script>
<style>
  .esri-ui-corner .esri-component{
    display: none !important;
  }
  .el_zoom{
    position: absolute;
    top: 80px;
    left: 40px;
  }
  table tr{
    line-height: 10px;
  }
  .el-slider__button{
    width:8px !important;
    height: 8px !important;
  }
  .el-slider-imgbtn{
    cursor: pointer;
  }

  #imgPanUp:hover {
    content: url('../assets/img/NavDishTop_Selected.png');
  }

  #imgPanHome:hover{
    content: url('../assets/img/NavDishCenter_Selected.png');
  }

  #imgPanLeft:hover{
    content: url('../assets/img/NavDishLeft_Selected.png');
  }

  #imgPanRight:hover{
    content: url('../assets/img/NavDishRight_Selected.png');
  }

  #imgPanDown:hover{
    content: url('../assets/img/NavDishBottom_Selected.png');
  }
  
  #imgZoomUp:hover{
    content: url('../assets/img/ZoomBarTop_Selected.png');
  }

  #imgZoomDown:hover{
    content: url('../assets/img/ZoomBarBottom_Selected.png');
  }

  .esri-attribution{
    display: none !important;
  }

  /* popup弹窗 */
  .esri-popup__header{
    background: #444444;
    color:#fff;
    border-radius: 5px 5px 0px 0px;
  }
  .esri-popup__header-title, h1.esri-popup__header-title{
    padding: 4px 0px !important;
    text-align: left;
    margin-left: 6px;
  }
  .esri-popup__button{
    padding: 4px 0px !important;
  }
  .esri-popup__button--dock{
    display: none;
  }
  .esri-icon-close{
    background: url(../assets/img/popup-sprite.png) no-repeat;
    right: 3px;
    background-position: -34px 0;
    width: 12px;
    height: 17px;
    border:0px;
  }
  .esri-icon-close:before{
    content:'';
  }
  .esri-popup__feature-buttons{
    display: none;
  }
  .esri-popup__feature-menu{
    display: none;
  }

  /* .title{
    background-color: #444444;
    color: #FFFFFF;
    line-height: 20px;
    padding-left: 6px;
    border-radius: 5px 5px 0px 0px;
    -moz-border-radius: 5px 5px 0px 0px;
    -webkit-border-radius: 5px 5px 0px 0px;
    -o-border-radius: 5px 5px 0px 0px;
    cursor: default;
    border: 1px solid #666666;
    border-bottom: none;
    text-align: left;
  }

  .titleButton{
    position: absolute;
    top: 2px;
    cursor: pointer;
    background: url(../assets/img/popup-sprite.png) no-repeat;
  }
  .titleButton.close{
    right: 3px;
    background-position: -34px 0;
    width: 12px;
    height: 17px;
  } */
  .esri-popup__content{
    margin:0px;
  }
  .esri-view-width-xlarge .esri-popup__main-container{
    width:180px;
  }
  .esri-popup__pointer-direction{
    background-color:#444444;
  }
  .esri-popup--shadow{
    border-radius: 13px 13px 0px 0px !important;
  }

</style>

