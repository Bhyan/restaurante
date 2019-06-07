$(document).ready(function () {

    $("#comprar").click(function () {

        const data = $('input').serialize();
        const url = $(this).data('url');

        $.ajax({
            method: "POST",
            url: url,
            data: data
        }).done(function (data) {
            $("#divCompra").html(data)
        }).fail(function (jqXHR, textStatus ) {
            alert("error - "+jqXHR.responseText);
        }).always(function () {});
    });

    $("#checkboxAll").click(function () {
        $(".checkboxId").each(function () {
           $(this).trigger('click')
        });
    });

    $(".checkboxId:checked").click(function () {
        let $compra = 0;

        $('input:checked').each(function(){
            const $id = $(this).prop('value');

            $compra += $('#qtdCompra_'+ $id).val() * $('#precoUni_'+ $id).text();
        });

        $("#totalCompra b").html('Total R$ ' + $compra);
    });

    $('.qtdCompra').click(function () {
        const $id = $this.prop('id').slice(-1);
        $('#checkboxId_' + $id).prop('checked', true);
    });

    $('.checkboxId').click(function () {
        const $id = $(this).val();
        const $val = $('#qtdCompra_' + $id).val() == 0 ? 1 : 0;

        $('#qtdCompra_' + $id).val($val);
    });

});


