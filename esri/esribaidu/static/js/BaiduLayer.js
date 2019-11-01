define(["dojo/_base/declare", "dojo/_base/lang","esri/config","esri/layers/BaseTileLayer","esri/request"],
    function (declare,lang,esriConfig,BaseTileLayer,esriRequest) {
        return BaseTileLayer.createSubclass({
            properties: {
                urlTemplate: null
            },
            getTileUrl: function(level, row, col) {
                var zoom = level - 1;
                var offsetX = Math.pow(2, zoom);
                var offsetY = offsetX - 1;
                var numX = col - offsetX;
                var numY = (-row) + offsetY;
                zoom = level + 1;
                var url = "http://online1.map.bdimg.com/tile/?qt=tile&x=" + numX + "&y=" + numY + "&z=" + zoom + "&styles=pl";
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
    }
)