$(function () {

    let url = 'http://localhost:8780/cart/showItem'
    let params = {
        "userId": 100
    }
    $.post(url, params, callBack);

    function callBack(resp) {
        if (resp.status == true) {

            let data = resp.data;

            var total_price_cart = 0;
            var total_price_cart_preferential = 0;
            var product_list = $('#product-list');
            product_list.empty();
            for (const i of data) {
                var product_item = $('<div id="product_item" data-bind="rowid:1" class="item item_selected "></div>')
                var item_form = $('<div class="item_form clearfix"></div>')
                var cell_p_checkbox = $('<div class="cell p-checkbox"></div>')
                var input1 = $('<input data-bind="cbid:1" class="checkbox" type="checkbox" name="checkItem" checked="" value="11345721-1">')
                cell_p_checkbox.append(input1)
                var cell_p_goods = $('<div class="cell p-goods"></div>')
                var p_img = $('<div class="p-img"></div>')
                var a1 = $('<a target="_blank"></a>')
                a1.attr("href", "/item/" + i.item.itemId);
                var image1 = $('<image  clstag="clickcart|keycount|xincart|p-imglistcart" width="52" height="52">')
                image1.attr("src", '' + i.item.image + '');
                image1.attr("alt", i.item.title)
                a1.append(image1);
                var p_name = $('<div class="p-name"></div>')
                var a2 = $('<a  clstag="clickcart|keycount|xincart|productnamelink" target="_blank"></a>')
                // a2.attr("href", "/item/" + i.item.itemId);
                a2.attr("href", "/cart/cart-item.html");
                a2.text(i.item.title);
                var span1 = $('<span class="promise411 promise411_11345721" id="promise411_11345721"></span>')
                p_name.append(a2);
                p_name.append(span1);
                var cell_p_price = $('<div class="cell p-price"></div>')
                var span2 = $('<span class="price" ></span>')
                span2.text("¥" + " " + i.item.price)
                cell_p_price.append(span2);
                var cell_p_promotion = $('<div class="cell p-promotion"></div>')
                var cell_p_inventory_stock_11345721 = $('<div class="cell p-inventory stock-11345721"></div>')
                cell_p_inventory_stock_11345721.text("有货")
                var cell_p_quantity = $('<div class="cell p-quantity" for-stock="for-stock-11345721"></div>')
                var quantity_form = $('<div class="quantity-form" data-bind=""></div>')
                cell_p_quantity.append(quantity_form);
                var a3 = $('<a  class="decrement" clstag="clickcart|keycount|xincart|diminish1" id="decrement"></a>')
                a3.attr("href", "javascript:void(0);")
                quantity_form.append(a3)
                var input2 = $('<input type="text" class="quantity-text"  id="changeQuantity-11345721-1-1-0">')
                a3.text("-");
                input2.attr("itemPrice", i.item.price);
                input2.attr("itemId", i.item.itemId);
                input2.attr("value", i.buyNum);
                quantity_form.append(input2);
                var a4 = $('<a href="javascript:void(0);" class="increment" clstag="clickcart|keycount|xincart|add1" id="increment"></a>')
                a4.attr("href", "javascript:void(0);")
                a4.text("+")
                quantity_form.append(a4);
                var cell_p_remove = $('<div class="cell p-remove"></div>')
                var a5 = $('<a id="remove-11345721-1" data-more="removed-87.20-1" clstag="clickcart|keycount|xincart|btndel318558" class="cart-remove" href="/cart/delete/${cart.id}.html"></a>')
                a5.attr("href", "/cart/delete/" + i.item.itemId);
                a5.text("删除");
                cell_p_remove.append(a5);

                cell_p_goods.append(p_img)
                    .append(p_name)
                cell_p_quantity.append(quantity_form)
                product_list.append(product_item).append(item_form)
                product_item.append(item_form);
                item_form.append(cell_p_checkbox)
                    .append(cell_p_goods)
                    .append(cell_p_price)
                    .append(cell_p_promotion)
                    .append(cell_p_inventory_stock_11345721)
                    .append(cell_p_quantity)
                    .append(cell_p_remove)

                total_price_cart = total_price_cart + (i.item.price) * i.buyNum;
            }


        }
    }



});