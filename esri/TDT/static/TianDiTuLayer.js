define(["dojo/_base/declare","esri/geometry/Extent", "esri/layers/tiled", "esri/layers/WMTSLayer", "esri/layers/TiledMapServiceLayer", "esri/layers/WMTSLayerInfo", "esri/layers/TileInfo", "esri/SpatialReference"], 
function(declare, Extent, tiled, WMTSLayer, TiledMapServiceLayer, WMTSLayerInfo, TileInfo, SpatialReference){
	return declare(esri.layers.TiledMapServiceLayer, {
		constructor: function() {
			this.spatialReference = new esri.SpatialReference({ wkid:4326 });
			this.initialExtent = (this.fullExtent = new esri.geometry.Extent(-180.0, -90.0, 180.0, 90.0, this.spatialReference));
			this.tileInfo = new esri.layers.TileInfo({
	            "rows" : 256,
	            "cols" : 256,
	            "compressionQuality" : 0,
	            "origin" : {
	              "x" : -180.0,
	              "y" : 90.0
	            },
	            "spatialReference" : {
	            	"wkid" : 4326
	            },
	            
	            "lods" : [
	              {"level" : 1, "resolution" : 0.7039130078555585, "scale" : 2.95828763796E8},
	              {"level" : 2, "resolution" : 0.35156249999999994, "scale" : 1.47914381898E8},
	              {"level" : 3, "resolution" : 0.17578124999999997, "scale" : 7.39571909489E7},
	              {"level" : 4, "resolution" : 0.08789062500000014, "scale" : 3.69785954745E7},
	              {"level" : 5, "resolution" : 0.04394531250000007, "scale" : 1.84892977372E7},
	              {"level" : 6, "resolution" : 0.021972656250000007, "scale" : 9244648.86862},
	              {"level" : 7, "resolution" : 0.01098632812500002, "scale" : 4622324.43431},
	              {"level" : 8, "resolution" : 0.00549316406250001, "scale" : 2311162.21716},
	              {"level" : 9, "resolution" : 0.0027465820312500017, "scale" : 1155581.10858},
	              {"level" : 10, "resolution" : 0.0013732910156250009, "scale" : 577790.554289},
	              {"level" : 11, "resolution" : 0.000686645507812499, "scale" : 288895.277144},
	              {"level" : 12, "resolution" : 0.0003433227539062495, "scale" : 144447.638572},
	              {"level" : 13, "resolution" : 0.00017166137695312503, "scale" : 72223.819286},
	              {"level" : 14, "resolution" : 0.00008583068847656251, "scale" : 36111.909643},
	              {"level" : 15, "resolution" : 0.000042915344238281406, "scale" : 18055.954822},
	              {"level" : 16, "resolution" : 0.000021457672119140645, "scale" : 9027.977411},
	              {"level" : 17, "resolution" : 0.000010728836059570307, "scale" : 4513.9887055},
	              {"level" : 18, "resolution" : 0.000005364418029785169, "scale" : 2256.99435275}
	            ]
	            
	        });			
			this.loaded = true;
	        this.onLoad(this);
		},
		getTileUrl: function(level, row, col) {
	        return this.url+"?SERVICE=WMTS&REQUEST=GetTile&VERSION=1.0.0&TileMatrixSet=guobiao&TILEMATRIX=" + (level-1) + "&TILEROW=" + row + "&TILECOL=" + col;
		}
	})
});
	