
$(function () {

$.ajaxSetup({
    async: false
});

    showItemList();


    TTCart.refreshTotalPrice();


    //  删除商品按钮的点击事件
    $('.removeItem').click(function () {

        let itemId = $(this).attr('itemId');
        let url = 'http://localhost:8780/cart/cartRemItem/'+itemId+'';
        let param = {
            itemId: itemId
        };
        $.post(url, param, function () {
            // showItemList();
            window.location.reload();
        })

    });




    // 批量删除
    $('#remove-batch').click(function () {

        let arr = [] ;
        $.each($('input:checkbox:checked'), function (value) {

            let a = $(this).val();
            if (a != null && a != '') {
                arr.push('itemId:' + a );
            }
        });

        if (arr.length != 0){
            let url = 'http://localhost:8780/cart/batchRem';
            let param = {
                str: arr.join(',')
            };
            $.post(url, param, function (resp) {
                // showItemList();
                window.location.reload();
            })
        }
    });




    // 跳转到item页面
    $('.jumpItem').click(function () {

        let itemId = $(this).attr('itemId');
        window.localStorage.setItem("itemId",itemId );
        window.location.href = '/cart/cart-item.html';
    });


    // 选中
    $('.checkbox').click(function () {
        TTCart.refreshTotalPrice();
    });


    // 如果库存不足不可以选
    $('.p-inventory').each(function (i, e) {
       let inventory = $(this).text();
       if (inventory === '库存不足') {
           let checkBox = $(this).parent().children().eq(0).children().eq(0);
           checkBox.prop('checked', false);
           checkBox.prop('disabled', 'disabled');
       }
    });


    // 跳转到结算页面

    $('#toSettlement').click(function () {

        let arr = [];

    $(".checkbox").each(function (i, e) {
        let _thisCheck = $(e);
        if (_thisCheck.attr('checked') === 'checked'){
         arr.push(_thisCheck.val());
         // arr.push({itemId: itemId,num: itemNum});
        }
        
        });

        if (arr.length === 0){
            alert('您没有选择任何商品,不能去结算');
        } else {
            let totalPrice = $(".totalSkuPrice").html();
            window.localStorage.setItem("itemList", arr);
            window.localStorage.setItem("totalPrice", totalPrice);
            window.location.href = '/order/order-cart.html';
        }
    })



});


function showItemList() {
    let url = 'http://localhost:8780/cart/showItem';
    // let params = {
    //     "userId": 100
    // };
    $.post(url, callBack);

    function callBack(resp) {
        if (resp.status == true) {

            let data = resp.data;

            let itemList = $('#product-list');
            itemList.empty();

            for (let i of data){

                //  每个商品最外层的大div
                let parentDiv = $('<div id="product_'+ i.item.itemId +'" data-bind="rowid:1" class="item item_selected"></div>')
                // 每个商品外层最大div内嵌的div
                let itemDiv = $('<div class="item_form clearfix"></div>');
                //  多选按钮
                let checkButton = $('<div class="cell p-checkbox"><input data-bind="cbid:1" class="checkbox" type="checkbox" name="checkItem" value="'+i.item.itemId+'"></div>')


                //  商品信息(图片,名字)包含的div
                let goodsDiv = $('<div class="cell p-goods"></div>');
                // 商品图片 链接
                let imgDiv = $('<div class="p-img"></div>');
                let imageHref = $('<a href="javascript:void(0);" class="jumpItem" itemid="'+i.item.itemId+'" target="_blank"></a>');
                let image = $('<img clstag="clickcart|keycount|xincart|p-imglistcart" width="52" height="52"/>');
                image.attr("src", i.item.image);
                image.attr("alt", i.item.title);
                imageHref.append(image);
                imgDiv.append(imageHref);

                //  商品名称
                let nameDiv = $('<div class="p-name"></div>');
                let nameHref = $('<a href="javascript:void(0);" class="jumpItem" itemid="'+i.item.itemId+'" clstag="clickcart|keycount|xincart|p-imglistcart" target="_blank"></a>')
                nameHref.text(i.item.title);
                let span = $('<span class="promise411 promise411_11345721" id="promise411_'+i.item.itemId+'"></span>');
                nameDiv.append(nameHref).append(span);

                // shop价格
                let priceDiv = $('<div class="cell p-price"><span class="price">¥'+i.item.price / 100+'</span></div>');
                // 优惠
                let by = $('<div class="cell p-promotion p-price" style="color:red;"><span id="d-'+i.item.itemId+'" class="price" style="color: orangered"></span></div>');
                // 库存情况
                let stock = $('<div class="cell p-inventory stock-11345721"></div>');

                let snum = i.item.num - i.buyNum;
                if (snum > 50){
                    stock.text('库存充足');
                } else if (snum > 0) {
                    stock.text('即将断货');
                } else {
                    stock.text('库存不足')
                }


                // 加减数量
                let quantityDiv = $('<div class="cell p-quantity" itemId="'+i.item.itemId+'"></div>');
                let quan = $('<div class="quantity-form" itemid="'+i.item.itemId+'"></div>');
                let desc = $(' <a href="javascript:void(0);" class="decrement" clstag="clickcart|keycount|xincart|diminish1" id="desc-'+i.item.itemId+'">-</a>')
                let quantity = $('<input type="text" class="quantity-text" itemPrice="'+i.item.price+'" itemId="'+i.item.itemId+'" value="'+i.buyNum+'" id="changeQuantity-'+i.item.itemId+'">');
                let incre = $(' <a href="javascript:void(0);" class="increment" clstag="clickcart|keycount|xincart|add1" id="incre-'+i.item.itemId+'">+</a>')
                quan.append(desc).append(quantity).append(incre);
                quantityDiv.append(quan);

                // 删除按钮
                let delDiv = $('<div class="cell p-remove"></div>');
                let delHref = $('<a id="remove'+i.item.itemId+'" data-more="removed-87.20-1" itemId="'+i.item.itemId+'" clstag="clickcart|keycount|xincart|btndel318558" class="removeItem" href="javascript:void(0);"></a>');
                delHref.text('删除');
                delDiv.append(delHref);

                goodsDiv.append(imgDiv).append(nameDiv);
                itemDiv.append(checkButton).append(goodsDiv).append(priceDiv)
                    .append(by).append(stock).append(quantityDiv).append(delDiv);

                parentDiv.append(itemDiv);

                itemList.append(parentDiv);


                // 计算总数量和总价格
                let currentNum = i.buyNum;
                let currentTotalPrice = currentNum * (i.item.price / 100);


                let disSpan =  $('#d-'+i.item.itemId+'');
                if (currentTotalPrice > 99999){
                    disSpan.text('-' + new Number(currentTotalPrice * 0.2).toFixed(2));

                } else if (currentTotalPrice > 50000){
                   disSpan.text('-' + new Number(currentTotalPrice * 0.1).toFixed(2))
                }  else if (currentTotalPrice > 999){
                    disSpan.text('-' + new Number(currentTotalPrice * 0.05).toFixed(2))
                }
                else{
                    disSpan.text('-0.00');
                }



            }

        }
    }
}











