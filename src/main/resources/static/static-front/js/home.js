/**
 *  有 single_product 这个类的组件的点击事件
 */


(function ($) {
 "use strict";

        //---------------------------------------------
        //Nivo slider
        //---------------------------------------------
             $('#nivoslider').nivoSlider({
                effect: 'random',
                slices: 15,
                boxCols: 8,
                boxRows: 4,
                animSpeed: 500,
                pauseTime: 5000,
                startSlide: 0,
                directionNav: true,
                controlNavThumbs: false,
                 controlNav: false,
                pauseOnHover: false,
                manualAdvance: false
             });


})(jQuery);



