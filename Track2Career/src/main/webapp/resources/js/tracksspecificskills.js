$(document).ready(function() {
    $('#showMore').click(function() {
        var val = $(this).text();
        if (val === 'Show more skills') {
            $(".viewMore").slideDown( "slow");
//            $('html, body').animate({
//                scrollTop: $("#viewMore").offset().top - 100
//            }, 1000);
            $(this).text("Hide more skills");
        }
        else {
            $(".viewMore").hide('fast');
            $('html, body').animate({
                scrollTop: $("#skill-overview").offset().top - 100
            }, 1000);
            $(this).text("Show more skills");
        }
    });
});