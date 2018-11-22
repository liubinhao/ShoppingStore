var TTCart = {
	load : function(){ // 加载购物车数据
		
	},
	itemNumChange : function(){
		$(".increment").click(function(){//＋
			var _thisInput = $(this).siblings("input");

			let checkBox = _thisInput.parent().parent().parent().children().eq(0).children().eq(0);

            if (checkBox.prop('disabled') != true) {
				checkBox.prop('checked', 'checked')
			}

			_thisInput.val(eval(_thisInput.val()) + 1);
			let itemId = _thisInput.attr('itemId');
			let buyNum = _thisInput.val();

			let param = {
				itemId: itemId,
				itemNum: buyNum
			};
			$.post("http://localhost:8780/cart/updItemNum/"+itemId+"/"+buyNum + "", param,function(data){
				TTCart.refreshTotalPrice();
				// showItemList();
                // window.location.reload();
			});
		});
		$(".decrement").click(function(){//-
			var _thisInput = $(this).siblings("input");

           let checkBox =  _thisInput.parent().parent().parent().children().eq(0).children().eq(0);
           if (checkBox.prop('disabled') != true){
           	checkBox.prop('checked', 'checked');
		   }

			if(eval(_thisInput.val()) == 1){
				return ;
			}
			_thisInput.val(eval(_thisInput.val()) - 1);

            let itemId = _thisInput.attr('itemId');
            let buyNum = _thisInput.val();

            let param = {
                itemId: itemId,
                itemNum: buyNum
            };
			$.post("http://localhost:8780/cart/updItemNum/"+itemId+"/"+buyNum+ "", param, function(data){
				TTCart.refreshTotalPrice();
				// showItemList();
				// window.location.reload();
			});
		});
		$(".quantity-form .quantity-text").rnumber(1);//限制只能输入数字
		$(".quantity-form .quantity-text").change(function(){
			var _thisInput = $(this);
            let itemId = _thisInput.attr('itemId');
            let buyNum = _thisInput.val();

            let param = {
                itemId: itemId,
                itemNum: buyNum
            };
			$.post("http://localhost:8780/cart/updItemNum/"+itemId+"/"+ buyNum, param, function(data){
				TTCart.refreshTotalPrice();
			});
		});
	},
	refreshTotalPrice : function(){ //重新计算总价
		var total = 0;
		var count = 0;
		var discount = 0;

            $(".p-promotion").each(function (i, e) {
				var _this = $(e);
				var check = _this.parent().children().eq(0).children().eq(0).attr('checked');
				if (check === 'checked') {
                    discount += Math.abs(_this.text());
                }
            });

            $(".quantity-form .quantity-text").each(function (i, e) {
                var _this = $(e);
				var check =	_this.parent().parent().parent().children().eq(0).children().eq(0).attr('checked');
				if (check === 'checked') {
                    total += (eval(_this.attr("itemPrice")) * 10000 * eval(_this.val())) / 10000;
                    count += (eval(_this.val()))
                }
            });
		// $(".totalSkuPrice").html('￥' + new Number(total).toFixed(2)).priceFormat({ //价格格式化插件
		// 	 prefix: '￥',
		// 	 thousandsSeparator: ',',
		// 	 centsLimit: 2
		// });
		$("#totalPrice").html(new Number(total / 100).toFixed(2));
		$(".totalSkuPrice").html('￥' + new Number((total / 100) - discount ).toFixed(2));
		$("#selectedCount").html(count);
		$('#totalRePrice').html(new Number(discount).toFixed(2));

	}
};

$(function(){
	TTCart.load();
	TTCart.itemNumChange();
});