		 jQuery.noConflict();(function($) {
            $(function() {
                 /* �Q�����@��o�� */
               $('#date').mask('9999/99/99');
		 /* �w�Дԍ� */
               $('#gakuban').mask('9999999');
        		/* �d�b�ԍ� */
				//$('#denwa').mask('9999');
				/* �d�b�ԍ�2 */
				//$('#denwa2').mask('9999');
				/* �d�b�ԍ�3 */
               //$('#denwa3').mask('9999');
               /* �����ԍ� */
               $("#product").mask("a*-999-a999",{placeholder:" ",completed:function(){alert("You typed the following: "+this.val());}});
               /* �X�֔ԍ� */
               $("#zipcode").mask("999-9999");
            });
         })(jQuery);
	
	  var _gaq = _gaq || [];
	  _gaq.push(['_setAccount', 'UA-4011945-3']);
	  _gaq.push(['_trackPageview']);
	  (function() {
	    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
	    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
	    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
	  })();