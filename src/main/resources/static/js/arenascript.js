f = {};

f.registerMovement = function () {
    $('#moveUp').click(function () {
        $('#actionValue').val('w');
        $('#actionType').val('movement');
        $('#submitAction').click();
    });
    $('#moveDown').click(function () {
        $('#actionValue').val('s');
        $('#actionType').val('movement');
        $('#submitAction').click();
    });
    $('#moveRight').click(function () {
        $('#actionValue').val('d');
        $('#actionType').val('movement');
        $('#submitAction').click();
    });
    $('#moveLeft').click(function () {
        $('#actionValue').val('a');
        $('#actionType').val('movement');
        $('#submitAction').click();
    });
};

f.registerAction = function () {
    $('#fight').click(function () {
        $('#actionValue').val('fight');
        $('#actionType').val('combat');
        $('#submitAction').click();
    });
    $('#flight').click(function () {
        $('#actionValue').val('flight');
        $('#actionType').val('combat');
        $('#submitAction').click();
    });
};

f.addPlebsToMap = function () {
    var plebArray = contentString.split(" ");
    $.each(plebArray, function (i , val) {
        var plebLocation = val.split("_");
        //TODO Change this si we don't like the blue boxes visible;
        $('#tr' + plebLocation[0] + ' .td' + plebLocation[1]).css("background-color", "blue").addClass("plebLocation");
       // $('#tr' + plebLocation[0] + ' .td' + plebLocation[1]).addClass("plebLocation");
    });
};

f.mapInterfaceControl = function () {
    $('#tr' + playerX + ' .td' + playerY).css("background-color", "red");
};

f.sendFightOrFlight = function () {
    if($('#tr' + playerX + ' .td' + playerY).hasClass("plebLocation")) {
        alert("Enemy Found");
        f.registerAction();
    }
    else {
        f.registerMovement();
    }
};

$(document).ready(function () {
    f.addPlebsToMap();
    f.sendFightOrFlight();
    f.mapInterfaceControl();
});