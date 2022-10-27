<template>
            <section class="min-vh-100" style="background-color: #F4f7f7;">
            <div class="container py-5 h-100">
            <div class="row d-flex justify-content-center vh-100">
                <div class="col col-xl-7">
                <div class="card">
                    <div class="row">
                    <div class="d-flex">
                        <div class="card-body p-4 p-lg-5 text-black">
                        <form id="passwordForm" @submit.prevent="submit">    
                            <h4 class="fw-normal mb-4 "><strong>Change Password</strong></h4>     
                            <p class="fw-normal mb-3 alert alert-success" v-if="successMessage">{{ successMessage }}</p>
                            <p class="fw-normal mb-3 alert alert-danger" v-if="failMessage">{{ failMessage }}</p>
          
                            <div class="form-outline mb-4">
                                <label class="form-label" for="oldPassword">Old Password</label>
                                <input type="password" id="oldPassword" v-model="oldPassword" name="oldPassword" class="form-control form-control-lg" :class="{ 'border-danger': v$.oldPassword.$error }" />
                                <div class="form-label text-danger" v-if="v$.oldPassword.$error">Please enter your old password</div>
                            </div>   

                            <div class="form-outline mb-4">
                                <label class="form-label" for="newPassword">New Password</label>
                                <input type="password" id="newPassword" v-model="newPassword" name="newPassword" class="form-control form-control-lg" :class="{ 'border-danger': v$.newPassword.$error }" />
                                <div class="form-label text-danger" v-if="v$.newPassword.$error">Please enter a new password</div>
                            </div> 

                            <div class="pt-3">
                                <input class="btn btn-warning text-white btn-lg btn-block" type="submit" value="Change Password" />
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
    import { required } from '@vuelidate/validators'

    export default {
        name: 'ChangePassword',
        setup () {
            return { v$: useVuelidate() }
        },
        data() {
            return {
                oldPassword: '',
                newPassword: '',
                successMessage: null,
                failMessage: null,
            }
        },
        validations: {
            oldPassword: {
              required
            },
            newPassword: {
              required
            }
        },
        methods: {
            submit: function(e) {
                this.v$.$validate()
                if (!this.v$.$error) {
                    var passwordForm = new FormData();
                    passwordForm.append('oldPassword', this.oldPassword);
                    passwordForm.append('newPassword', this.newPassword);
                    axios({ method:'post', 
                        url: "http://localhost:8080/profile/password", 
                        data: passwordForm, 
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
