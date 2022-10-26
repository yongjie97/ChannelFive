<template>
    <section class="min-vh-100" style="background-color: #E0E0E0;">
            <div class="container py-5 h-100">
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
          
                          <form id="registerForm" @submit.prevent="submit">
                            <h4 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">Register Account</h4>
                            <p class="fw-normal mb-3 alert alert-success" v-if="successMessage">{{ successMessage }}</p>
                            <p class="fw-normal mb-3 alert alert-danger" v-if="failMessage">{{ failMessage }}</p>
          
                            <div class="form-outline mb-4">
                                <label class="form-label" for="email">Email address*</label>
                                <input type="email" id="email" v-model="email" name="email" class="form-control form-control-lg" :class="{ 'border-danger': v$.email.$error }" />
                                <div class="form-label text-danger" v-if="v$.email.$error">Please enter a valid email</div>
                            </div>
          
                            <div class="form-outline mb-4">
                                <label class="form-label" for="password">Password*</label>
                                <input type="password" id="password" v-model="password" name="password" class="form-control form-control-lg" :class="{ 'border-danger': v$.password.$error }" />
                                <div class="form-label text-danger" v-if="v$.password.$error">Please enter your password</div>
                            </div>
    
                            <div class="form-outline mb-4">
                                <label class="form-label" for="displayName">Display Name*</label>
                                <input type="text" id="displayName" v-model="displayName" name="displayName" class="form-control form-control-lg" :class="{ 'border-danger': v$.displayName.$error }" />
                                <div class="form-label text-danger" v-if="v$.displayName.$error">Please enter a display name</div>
                            </div>
          
                            <div class="pt-1 mb-4">
                              <input class="btn btn-warning text-white btn-lg btn-block" type="submit" value="Register"/>
                            </div>

                            <p class="mb-5 pb-lg-2 text-muted">Already have an account? <router-link class="text-muted" to="/login">Login</router-link></p>
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

    import { useVuelidate } from '@vuelidate/core'
    import { required, email } from '@vuelidate/validators'

    export default {
        name: 'Register',
        setup () {
            return { v$: useVuelidate() }
        },
        data() {
            return {
                email: null,
                password: null,
                displayName: null,
                successMessage: null,
                failMessage: null,
            }
        },
        validations: {
            email: {
              required,
              email
            },
            password: {
              required
            },
            displayName: {
              required
            },
        },
        methods: {
            submit: function(e) {
                this.v$.$validate()
                if (!this.v$.$error) {
                    var registerForm = new FormData();
                    registerForm.append('email', this.email);
                    registerForm.append('password', this.password);
                    registerForm.append('displayName', this.displayName);
                    axios({ method:'post', 
                        url: "http://localhost:8080/register", 
                        data: registerForm, 
                        headers: {"Content-Type":"multipart/form-data"},
                        withCredentials: true
                    }).then((res) => {
                      if (res.data.success) {
                          this.successMessage = res.data.message
                          this.failMessage = null
                      } else {
                          this.failMessage = res.data.message
                          this.successMessage = null
                      }
                    }).catch((error) => {
                        this.failMessage = "Opps! Something went wrong. Please try again later."
                        this.successMessage = null
                    })
                }
            }
        }
    }
</script>

<style scoped>
    .bg-custom {
        background-image: url('../../assets/image/register.jpg')
    }

</style>