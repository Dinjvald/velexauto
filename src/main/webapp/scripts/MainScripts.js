/**
 * Created by Dinjvald on 04/12/15.
 */

function menuHover() {
    $("ul#menu li#orders").hover(
        function () {
            $("ul#menu li ul#orders_submenu").slideDown(150);
        },
        function () {
            $("ul#menu li ul#orders_submenu").slideUp(150);
        });
}