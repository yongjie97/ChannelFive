<template>
  <section class="min-vh-100" style="background-color: #E0E0E0;">
    <div class="container py-5 h-100">
    <div class="row d-flex justify-content-center mt-5 vh-100">
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
                <form id="passwordRecoveryForm" @submit.prevent="submit">
                    <h4 class="fw-normal mb-4" style="letter-spacing: 1px;">Reset Password</h4>   
                    <p class="fw-normal mb-3 alert alert-success" v-if="successMessage">{{ successMessage }}</p>
                    <p class="fw-normal mb-3 alert alert-danger" v-if="failMessage">{{ failMessage }}</p>   
                    <div class="form-outline mb-4">
                        <label class="form-label" for="email">Email address</label>
                        <input type="email" id="email" v-model="email" name="email" class="form-control form-control-lg" :class="{ 'border-danger': v$.email.$error }" />
                        <div class="error text-danger text-right" v-if="v$.email.$error">Please enter a valid email</div>
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

    import { useVuelidate } from '@vuelidate/core'
    import { required, email } from '@vuelidate/validators'

    export default {
        name: 'ForgotPassword',
        setup () {
            return { v$: useVuelidate() }
        },
        data() {
            return {
                email: null,
                successMessage: null,
                failMessage: null
            }
        },
        validations: {
            email: {
                required,
                email
            }
        },
        methods: {
            submit: function(e) {
                this.v$.$validate()
                if (!this.v$.$error) {
                    var resetPasswordForm = new FormData();
                    resetPasswordForm.append('email', this.email);
                    axios({ method:'post', 
                        url: "http://localhost:8080/password/recovery", 
                        data: resetPasswordForm, 
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
 .btn-dark:hover {
    background-color: rgb(64, 64, 64);
    border-color: rgb(64, 64, 64);
 }
 .btn-dark:active {
    background-color: rgb(103, 103, 103);
    border-color: rgb(103, 103, 103);
 }

</style>