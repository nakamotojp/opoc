/* Created by jankoatwarpspeed.com */

(function($) {
    $.fn.formToWizard_list = function(options) {
        options = $.extend({  
            submitButton: "",
            section: 0,
            ListSize:0
        }, options); 
        
        var element = this;

        var steps = $(element).find("fieldset");
        var count = steps.size(); 
        var submmitButtonName = "#" + options.submitButton;
        var sectionId = options.section;
        var ListSize = options.ListSize;
        if(ListSize<=10){return;}
        $(submmitButtonName).hide();

        // 2
        $(element).before("<ul id='steps'></ul>");

        steps.each(function(i) {
            $(this).wrap("<div id='step" + i + "sec" + sectionId + "'></div>");
            $(this).append("<p id='step" + i + "sec" + sectionId + "commands' ></p>");

            // 2
            var name = $(this).find("legend").html();
     
            if (i == 0) {
                createNextButton(i);
                selectStep(i);
            }
            else if (i == count - 1) {
                $("#step" + i + "sec" + sectionId).hide();
                createPrevButton(i);
            }
            else {
                $("#step" + i + "sec" + sectionId).hide();
                createPrevButton(i);
                createNextButton(i);
            }
        });

        function createPrevButton(i) {
            var stepName = "step" + i + "sec" + sectionId;
            $("#" + stepName + "commands").append("<a href='#' id='" + stepName + "Prev' class='prev'></a>");

            $("#" + stepName + "Prev").bind("click", function(e) {
                $("#" + stepName).hide();
                $("#step" + (i - 1) + "sec" + sectionId).show();
                $(submmitButtonName).hide();
                selectStep(i - 1);
            });
        }

        function createNextButton(i) {
            var stepName = "step" + i + "sec" + sectionId;
            $("#" + stepName + "commands").append("<a href='#' id='" + stepName + "Next' class='next'></a>");

            $("#" + stepName + "Next").bind("click", function(e) {
                $("#" + stepName).hide();
                $("#step" + (i + 1) + "sec" + sectionId).show();
                if (i + 2 == count)
                    $(submmitButtonName).show();
                selectStep(i + 1);
            });
        }

        function selectStep(i) {
            $("#steps li").removeClass("current");
            $("#stepDesc" + i).addClass("current");
        }

    }
})(jQuery); 