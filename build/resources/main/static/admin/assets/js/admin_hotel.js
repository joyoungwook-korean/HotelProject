var main = {
    init : function () {

        $('.crud_change').attr('disabled',true);
        $('#update_crud').attr('disabled',true);
        $('#delete_crud').attr('disabled',true);



        $('.crud_change').on('input',function () {
             $('#delete_crud').attr('disabled',true);
        });
    }

}
main.init();