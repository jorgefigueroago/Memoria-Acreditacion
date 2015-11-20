function ActualizarBandejaEventos(){
$("#panel0").addClass("panel-warning");                                            
    var s0 = $("#evento0");
    var s1 = $("#evento1");
    var s2 = $("#evento2");
    var s3 = $("#evento3");

    var l0 = $("#lugar0");
    var l1 = $("#lugar1");
    var l2 = $("#lugar2");
    var l3 = $("#lugar3");

    var f0 = $("#fecha0");
    var f1 = $("#fecha1");
    var f2 = $("#fecha2");
    var f3 = $("#fecha3");


    var e0 = $("#expositor0");
    var e1 = $("#expositor1");
    var e2 = $("#expositor2");
    var e3 = $("#expositor3");

    var es0 = $("#estado0");
    var es1 = $("#estado1");
    var es2 = $("#estado2");
    var es3 = $("#estado3");


    var prueba = $("#prueba");

    s0.html("#{eventoController.items[0].titulo}");
    s1.html("#{eventoController.items[1].titulo}");
    s2.html("#{eventoController.items[2].titulo}");
    s3.html("#{eventoController.items[3].titulo}");

    l0.html("#{eventoController.items[0].lugar}");
    l1.html("#{eventoController.items[1].lugar}");
    l2.html("#{eventoController.items[2].lugar}");
    l3.html("#{eventoController.items[3].lugar}");

    e0.html("#{eventoController.items[0].idExpositor.nombre}"+ " #{eventoController.items[0].idExpositor.apellido}");
    e1.html("#{eventoController.items[1].idExpositor.nombre}"+ " #{eventoController.items[1].idExpositor.apellido}");
    e2.html("#{eventoController.items[2].idExpositor.nombre}"+ " #{eventoController.items[2].idExpositor.apellido}");
    e3.html("#{eventoController.items[3].idExpositor.nombre}"+ "#{eventoController.items[3].idExpositor.apellido}");
                                    
    //prueba.html("#{eventoController.items[3].estado}");
    if('#{eventoController.items[0].estado}' == "Difundido"){

    $("#panel0").removeClass("panel-default");
    $("#panel0").addClass("panel-success");                                            
    }
    if('#{eventoController.items[0].estado}' == "Realizado"){

    $("#panel0").removeClass("panel-default");
    }
    //-----------------
    if('#{eventoController.items[1].estado}' == "Difundido"){

    $("#panel1").removeClass("panel-default");
    $("#panel1").addClass("panel-success");                                            
    }
    if('#{eventoController.items[1].estado}' == "Realizado"){

    $("#panel1").removeClass("panel-default");
    $("#panel1").addClass("panel-warning");                                            
    }
    //-----------------
    if('#{eventoController.items[2].estado}' == "Difundido"){

    $("#panel2").removeClass("panel-default");
    $("#panel2").addClass("panel-success");                                            
    }
    if('#{eventoController.items[2].estado}' == "Realizado"){

    $("#panel2").removeClass("panel-default");
    $("#panel2").addClass("panel-warning");                                            
    }
    //-----------------
    if('#{eventoController.items[3].estado}' == "Difundido"){

    $("#panel3").removeClass("panel-default");
    $("#panel3").addClass("panel-success");                                            
    }
    if('#{eventoController.items[3].estado}' == "Realizado"){

    $("#panel3").removeClass("panel-default");
    $("#panel3").addClass("panel-warning");                                            
    }
    //-----------------



    }
    setInterval(ActualizarBandejaEventos);