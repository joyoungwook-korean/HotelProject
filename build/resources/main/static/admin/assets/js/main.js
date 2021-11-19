$.noConflict();

jQuery(document).ready(function ($) {

    $('#user_tr').on('click',function () {
       alert('aaa');
    });

    "use strict";

    [].slice.call(document.querySelectorAll('select.cs-select')).forEach(function (el) {
        new SelectFx(el);
    });

    jQuery('.selectpicker').selectpicker;


    $('.search-trigger').on('click', function (event) {
        event.preventDefault();
        event.stopPropagation();
        $('.search-trigger').parent('.header-left').addClass('open');
    });

    $('.search-close').on('click', function (event) {
        event.preventDefault();
        event.stopPropagation();
        $('.search-trigger').parent('.header-left').removeClass('open');
    });

    $('.equal-height').matchHeight({
        property: 'max-height'
    });

    // var chartsheight = $('.flotRealtime2').height();
    // $('.traffic-chart').css('height', chartsheight-122);


    // Counter Number
    $('.count').each(function () {
        $(this).prop('Counter', 0).animate({
            Counter: $(this).text()
        }, {
            duration: 3000,
            easing: 'swing',
            step: function (now) {
                $(this).text(Math.ceil(now));
            }
        });
    });


    // Menu Trigger
    $('#menuToggle').on('click', function (event) {
        var windowWidth = $(window).width();
        if (windowWidth < 1010) {
            $('body').removeClass('open');
            if (windowWidth < 760) {
                $('#left-panel').slideToggle();
            } else {
                $('#left-panel').toggleClass('open-menu');
            }
        } else {
            $('body').toggleClass('open');
            $('#left-panel').removeClass('open-menu');
        }

    });


    $(".menu-item-has-children.dropdown").each(function () {
        $(this).on('click', function () {
            var $temp_text = $(this).children('.dropdown-toggle').html();
            $(this).children('.sub-menu').prepend('<li class="subtitle">' + $temp_text + '</li>');
        });
    });


    // Load Resize
    $(window).on("load resize", function (event) {
        var windowWidth = $(window).width();
        if (windowWidth < 1010) {
            $('body').addClass('small-device');
        } else {
            $('body').removeClass('small-device');
        }

    }),
        $('#find_user').on('input', function () {
            var find_user = $('#find_user').val();
            var save_tr = $('#user_tbody').clone();

            $.ajax({
               url: '/admin',
                type: 'post',
                data:find_user,
                contentType: 'application/json; charset=utf-8'
            }).done(function (data) {
                $('#user_tbody').remove();

                let $newTbody =null;
                $newTbody = $("<tbody class='new-tbody' id='user_tbody'>" +
                    "</tbody>");
                $('.table_user').append($newTbody);

                for(var i=0; i<data.length; i++){
                    let $aaaa = $("<tr id='user_tr' >" +
                        "<td>"+i+"</td>" +
                        "<td>"+data[i].provider+"</td>" +
                        "<td>"+data[i].name+"</td>" +
                        "<td>"+data[i].email+"</td>" +
                        "<td>"+'없음'+"</td>" +
                        "<td>"+'도쿄'+"</td>" +
                        "<td>"+
                            "<span class='badge badge-complete'>"+data[i].role+"</span>"
                    +"</td>"+
                        "</tr>")
                    $newTbody.append($aaaa);

                }



            }).fail(function (error) {

            });

        });



});