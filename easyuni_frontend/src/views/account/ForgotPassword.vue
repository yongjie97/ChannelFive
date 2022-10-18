<template>
  <section class="vh-100" style="background-color: #E0E0E0;">
    <div class="container py-5 h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
        <div class="col col-xl-10">
        <div class="card">
            <div class="row g-0">
                <div class="col-md-6 col-lg-5 d-flex align-items-center bg-dark">
                    <div class="text-white py-4 p-md-5 mx-md-4 mx-4">
                        <h4 class="mb-4 ">Forgot your password?</h4>
                        <p class="small mb-0">Enter your email and check your inbox for instructions. Please also check your spam folder.</p>
                    </div>
                </div>
            <div class="col-md-6 col-lg-7 d-flex align-items-center">
                <div class="card-body p-4 p-lg-5 text-black">
                <form id="passwordRecoveryForm" action="/recovery" method="post">      
                    <h4 class="fw-normal mb-4" style="letter-spacing: 1px;">Reset Password</h4>      
                    <div class="form-outline mb-4">
                    <input type="email" id="email" name="email" class="form-control form-control-lg" />
                    <label class="form-label" for="email">Email address</label>
                    </div>  
                    <div class="pt-1 mb-4">
                    <input class="btn btn-dark btn-lg btn-block" type="submit" value="Send" />
                    </div>
                </form>

                </div>
            </div>
            </div>
        </div>
        </div>
    </div>
    </div>
  </section>
</template>
    
<script>
    export default {
      name: 'ForgotPassword'
    }
</script>