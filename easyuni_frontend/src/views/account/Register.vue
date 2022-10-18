<template>
    <section class="vh-110" style="background-color: #E0E0E0;">
            <div class="container py-5 h-110">
              <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col col-xl-10">
                  <div class="card">
                    <div class="row g-0">
                      <div class="col-md-6 col-lg-5 d-flex align-items-center bg-custom">
                        <div class="text-white py-4 p-md-5 mx-md-4 mx-4">
                            <h4 class="mb-4 ">Join the EasyUni Community</h4>
                            <p class="small mb-0">You will be able to partake in discussions and post reviews for university courses!</p>
                        </div>
                      </div>
                      <div class="col-md-6 col-lg-7 d-flex align-items-center">
                        <div class="card-body p-4 p-lg-5 text-black">
          
                          <form id="registerForm" action="/register" method="post">
                            <h4 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">Register Account</h4>
                            <p></p>
          
                            <div class="form-outline mb-4">
                              <input type="email" id="email" name="email" class="form-control form-control-lg" />
                              <label class="form-label" for="email">Email address*</label>
                            </div>
          
                            <div class="form-outline mb-4">
                              <input type="password" id="password" name="password" class="form-control form-control-lg" />
                              <label class="form-label" for="password">Password*</label>
                            </div>
    
                            <div class="form-outline mb-4">
                                <input type="text" id="displayName" name="displayName" class="form-control form-control-lg" />
                                <label class="form-label" for="displayName">Display Name*</label>
                            </div>
    
                            <div class="form-outline mb-4">
                                <input type="text" id="zipCode" name="zipCode" class="form-control form-control-lg" />
                                <label class="form-label" for="zipCode">Zip Code</label>
                            </div>
          
                            <div class="pt-1 mb-4">
                              <input class="btn btn-warning text-white btn-lg btn-block" type="submit" value="Register"/>
                            </div>
          
                            <p class="mb-5 pb-lg-2 text-muted">Already have an account? <a href="/login" class="text-muted">Login</a></p>
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
        name: 'Register'
    }
</script>

<style scoped>
    .bg-custom {
        background-image: url('../../assets/image/register.jpg')
    }

</style>