export default {
    BaseMapLayers:[
        // { id: "TiandituLayer_gx", title: "广西天地图", initVisible: true, opacity: 1, restURL: "http://10.45.252.105:8066/ime-server/rest/tdtgx_vec/wmts?SERVICE=WMTS&REQUEST=GetTile&VERSION=1.0.0&TileMatrixSet=guobiao&TILEMATRIX={level}&TILEROW={row}&TILECOL={col}" },
        // { id: "TiandituLayer_gxbz", title: "广西天地图标注", initVisible: true, opacity: 1, restURL: "http://10.45.252.105:8066/ime-server/rest/tdtgx_vecanno/wmts?SERVICE=WMTS&REQUEST=GetTile&VERSION=1.0.0&TileMatrixSet=guobiao&TILEMATRIX={level}&TILEROW={row}&TILECOL={col}" },
        { id: "TiandituLayer_gx", title: "广西天地图", initVisible: true, opacity: 1, restURL: "http://www.mapgx.com/ime-server/rest/tdtgx_vec/wmts?SERVICE=WMTS&REQUEST=GetTile&VERSION=1.0.0&TileMatrixSet=guobiao&TILEMATRIX={level}&TILEROW={row}&TILECOL={col}" },
        { id: "TiandituLayer_gxbz", title: "广西天地图标注", initVisible: true, opacity: 1, restURL: "http://www.mapgx.com/ime-server/rest/tdtgx_vecanno/wmts?SERVICE=WMTS&REQUEST=GetTile&VERSION=1.0.0&TileMatrixSet=guobiao&TILEMATRIX={level}&TILEROW={row}&TILECOL={col}" },
        { id: "TiandituLayer_gxyx", title: "广西影像天地图", initVisible: false, opacity: 1, restURL: "http://10.45.252.105:8066/ime-server/rest/tdtgx_img/wmts?SERVICE=WMTS&REQUEST=GetTile&VERSION=1.0.0&TileMatrixSet=guobiao&TILEMATRIX={level}&TILEROW={row}&TILECOL={col}" },
        { id: "TiandituLayer_gxyxbz", title: "广西天影像地图标注", initVisible: false, opacity: 1, restURL: "http://10.45.252.105:8066/ime-server/rest/tdtgx_imganno/wmts?SERVICE=WMTS&REQUEST=GetTile&VERSION=1.0.0&TileMatrixSet=guobiao&TILEMATRIX={level}&TILEROW={row}&TILECOL={col}" }
    ]
}