
let itemList = window.localStorage.getItem("itemList");
let totalPrice = window.localStorage.getItem("totalPrice");

$('#warePriceId').text(totalPrice);
$('#sumPayPriceId').text(totalPrice);
$('#payPriceId').text(totalPrice);


// let url = 'http://localhost:8780/cart/findShoppingList';
let url = cartUrl + 'cart/findShoppingList';
let param = {itemIds: itemList};

$.get(url, param,  shoppingList);

function shoppingList(resp) {

    if (resp.status === true){
        let goodsList = $('#goods-list');
        goodsList.empty();


        let data = resp.data;
        for (let i of data){


            // 商品的所有信息

            // 图片这一块
            let p_img = $('<div style="float: left" class="p-img"></div>');
            let imgHref = $('<a target="_blank" class="p-item" itemId="'+i.item.itemId+'" href="javaScript:void(0)"></a>');
            let image = $('<img width="82px" height="92px" alt="" />');
            image.attr('src', i.item.image);
            imgHref.append(image);
            p_img.append(imgHref);

            // 商品各种信息这一块
            let goodsDiv = $('<div class="goods-msg" style="float: left; margin-left: 20px ;"></div>');

            let p_name = $('<div class="p-name"></div>');
            let nameHref =$('<a href="javaScript:void(0)" class="p-item" itemId="'+i.item.itemId+'" target="_blank"></a>');
            nameHref.text(i.item.title);
            p_name.append(nameHref);

            let p_price =$('<div class="p-price"></div><br/>');
            let price = $('<strong>￥'+i.item.price / 100 +'</strong>');
            let buyNum = $('<span class="ml20">'+i.buyNum+'</span>');
            let inventory = $('<span class="ml20 p-inventory skuId='+i.item.itemId+'"></span>');
            let stock = i.item.num - i.buyNum;
            if (stock > 50){
                inventory.text('库存丰富');
            } else if (stock > 0) {
                inventory.text('即将断货');
            } else {
                inventory.text('库存不足,请重现选择商品')
            }
            p_price.append(price).append(buyNum).append(inventory);


            let know = $('<i class="p-icon p-icon-w"></i><span class="ftx-04">7天无理由退货</span>');

            goodsDiv.append(p_name).append('<br/>').append(p_price).append(know);


            let clr = $('<div class="clr"></div>');

            goodsList.append(p_img).append(goodsDiv).append(clr).append('<hr/>');


        }
    $('.p-item').click(function () {
       let itemId = $(this).attr('itemId');
       window.localStorage.setItem("itemId", itemId);
       window.location.href = '/cart/cart-item.html';
    });

    }



}

