f = {};

f.showSideBarNavigation = function() {
    $("#button1").click(function () {
        $(".midLeftSidebarNavigation").css("display", "block");
        $(".hideVisibleDropDown").css("display", "block");
    });
};

f.hideSideBarNavigation = function () {
    $(".hideVisibleDropDown").click(function(){
        $(".midLeftSidebarNavigation").css("display", "none");
        $(".hideVisibleDropDown").css("display", "none");
    });
};

f.addValueToHeroType = function () {
    $('#createNightelfMohawk').click(function () {
        $('#heroTypeInput').val('NightelfMohawk');
    });
    $('#createChuckTesta').click(function () {
        $('#heroTypeInput').val('ChuckTesta');
    });
    $('#createAdolfCritler').click(function () {
        $('#heroTypeInput').val('AdolfCritler');
    });
};

f.selectHero = function () {
    $(".selectHeroButton").click(function () {
        var id = this.id;
        $("#selectionName").val($('#'+id+' .heroName').text());
    })
};

f.selectAction = function () {
    $("#deleteHero").click(function () {
        $("#selectionAction").val("SelectForDeletion");
    })
    $("#selectHero").click(function () {
        $("#selectionAction").val("SelectForPlay")
    })
};

$(document).ready(function () {
    f.showSideBarNavigation();
    f.hideSideBarNavigation();
    f.addValueToHeroType();
    f.selectHero();
    f.selectAction();
});
