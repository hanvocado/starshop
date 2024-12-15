function sendOTP() {
        const email = document.getElementById('signUpEmail').value;

        if (email != null) {
	        fetch(`/auth/sendOTP?email=${email}`, {
	            method: 'POST',
	            headers: {
	                'Content-Type': 'application/json',
	            },
	        })
	            .then(response => {
	                if (response.ok) {
	                    console.log('Sent');
	                } else {
	                    console.error('Failed to send');
	                }
	            })
	            .catch(error => console.error('Error:', error));        	
        }
    }
	
    
      $(document).on('ready', function () {
        // INITIALIZATION OF SHOW PASSWORD
        // =======================================================
        $('.js-toggle-password').each(function () {
          new HSTogglePassword(this).init()
        });


        // INITIALIZATION OF FORM VALIDATION
        // =======================================================
        $('.js-validate').each(function() {
          $.HSCore.components.HSValidation.init($(this), {
            rules: {
              confirmPassword: {
                equalTo: '#signupSrPassword'
              }
            }
          });
        });
      });