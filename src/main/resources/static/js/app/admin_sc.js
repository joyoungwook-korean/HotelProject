var main = {

    init: function () {

        var idx = $('#1').text();
        var roleColorId = $('#roleColor' + idx);


        roleColorId.on('click', function f() {
            alert(roleColorId.text())
        });


    }
}
main.init();