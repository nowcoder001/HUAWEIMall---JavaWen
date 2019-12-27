var statusId = 0;   //全局收货地址id
var status = "";  //全局编辑判断
$(function(){

    //收货地址之类的信息不可为空否则不可提交
    $('#button-ok').click(function(event) {
        if (status == "edit") {
            $('#myAddress-form').attr('action',"shippingServlet?mod=updateAddress&shpId="+statusId);
        };
        var name = $("input[name='consignee']").val().length;
        var mobile = $("input[name='mobile']").val().length;
        var address = $("textarea[name='address']").val().length;
        if (name == 0) {
            $('#consignee-msg').html('<font color="red">请输入收货人</font>').attr('class','label-error');
            event.preventDefault();
        };
        if (mobile == 0) {
            $('#tel-msg').html('<font color="red">请输入手机号</font>').attr('class','label-error');
            event.preventDefault();
        };
        if (address == 0) {
            $('#address-msg').html('<font color="red">请输入详细地址</font>').attr('class','label-error');
            event.preventDefault();
        };
    });

    $('#mobile').click(function(event) {
        $('#mobile_id').attr('style','display: none');

        $('#tel-msg').html('').attr('class','');
    });

    $('#phone').click(function(event) {
        $('#phone_id').attr('style','display: none');

    });
    $('#address').click(function(event) {
        $('#address_id').attr('style','display: none');
        $('#address-msg').html('').attr('class','');
    });

    $('#consignee').click(function(event) {
        $('#consignee-msg').html('').attr('class','');
    });

/*
    $('#mobile').blur(function(event) {
        $('#mobile_id').attr('style','display: block; position: absolute; cursor: text; float: left; z-index: 2; color: rgb(153, 153, 153);');
    });

    $('#phone').blur(function(event) {
        $('#phone_id').attr('style','display: block; position: absolute; cursor: text; float: left; z-index: 2; color: rgb(153, 153, 153);');
    });
    $('#address').blur(function(event) {
        $('#address_id').attr('style','display: block; position: absolute; cursor: text; float: left; z-index: 2; color: rgb(153, 153, 153);');
    });*/

    $('input[name="defaultFlag"]').click(function(event) {
        if (status == "edit") {

        };
        $.ajax({
                url: 'shippingServlet?mod=selectDefault',
                type: 'post',
                dataType: 'text',
                data: {
                    shpId:statusId
                },
                success:function(data){

                    var count = parseInt(data);
                    if (count == 0) {
                        $('#default_address').text('');
                    }else{
                        $('input[name="defaultFlag"]').prop('checked',false);
                        $('#default_address').text('已有默认地址，不可再选择默认地址');
                    }
                }
            })

    });


})

function editAddress(obj,shpId){
    statusId = shpId;
    status = "edit";
    $.ajax({
            url: 'shippingServlet?mod=getShippingById',
            type: 'post',
            dataType: 'json',
            data: {
                id:shpId
            },
            success:function(data){
                document.getElementById("consignee").value = data[0].receiverName;
                document.getElementById("mobile").value = data[0].receiverMobile;
                document.getElementById("phone").value = data[0].receiverPhone;
                document.getElementById("address").value = data[0].receiverAddress;
                document.getElementById("zipCode").value = data[0].receiverZip;
                if (data[0].receiverDefault == 'false') {
                    document.getElementById('defaultFlag').checked = false;
                }else{
                    document.getElementById('defaultFlag').checked = true;
                }


                document.getElementById("button-ok").value = '确定';

                document.getElementById("mobile_id").setAttribute('style','display: none');
                document.getElementById("phone_id").setAttribute('style','display: none');
                document.getElementById("address_id").setAttribute('style','display: none');
            }
        })
}

function deleteAddress(trId,shpId){
    $.ajax({
            url: 'shippingServlet?mod=deleteAddress',
            type: 'post',
            dataType: 'text',
            data: {
                id:shpId
            },
            success:function(data){
                var count = parseInt(data);
                if (count > 0) {
                    document.getElementById("tr"+trId).setAttribute('style','display:none');
                }else{

                }
            }
        })
}

function resetAddress(){
    status = "";
    document.getElementById("consignee").value = "";
    document.getElementById("mobile").value = "";
    document.getElementById("phone").value = "";
    document.getElementById("address").value = "";
    document.getElementById("zipCode").value = "";
    document.getElementById('defaultFlag').checked = false;
    document.getElementById("button-ok").value = '添加新地址';

    document.getElementById("mobile_id").setAttribute('style','display: block; position: absolute; cursor: text; float: left; z-index: 2; color: rgb(153, 153, 153);');
    document.getElementById("phone_id").setAttribute('style','display: block; position: absolute; cursor: text; float: left; z-index: 2; color: rgb(153, 153, 153);');
    document.getElementById("address_id").setAttribute('style','display: block; position: absolute; cursor: text; float: left; z-index: 2; color: rgb(153, 153, 153);');
}