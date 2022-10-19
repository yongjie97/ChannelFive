<template>
    <section class="vh-120" style="background-color: #E0E0E0;">
        <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center h-100">
            <div class="col col-xl-7">
            <div class="card">
                <div class="row">
                <div class="d-flex">
                    <div class="card-body p-4 p-lg-5 text-black">
                    <form id="profileForm" @submit.prevent="submit">    
                        <h4 class="fw-normal mb-4 "><strong>My Profile</strong></h4>

                        <div class="form-outline mb-4">
                          <p style="white-space: pre-line" class="fw-normal mb-3 alert alert-success" v-if="successMessage">{{ successMessage }}</p>
                          <p style="white-space: pre-line" class="fw-normal mb-3 alert alert-danger" v-if="failMessage">{{ failMessage }}</p>   
                        </div>

                        <div class="form-outline mb-4">
                                <label class="form-label" for="email">Email address</label>
                                <input type="email" id="email" v-model="email" name="email" class="form-control form-control-lg" disabled />
                            </div>
          
                            <div class="form-outline mb-4">
                                <label class="form-label" for="displayName">Display Name</label>
                                <input type="text" id="displayName" v-model="displayName" name="displayName" class="form-control form-control-lg" :class="{ 'border-danger': v$.displayName.$error }" />
                                <div class="form-label text-danger" v-if="v$.displayName.$error">Please enter a display name</div>
                            </div>
    
                            <div class="form-outline mb-4">
                                <label class="form-label" for="zipCode">Zip Code</label>
                                <input type="text" id="zipCode" v-model="zipCode" name="zipCode" class="form-control form-control-lg" :class="{ 'border-danger': v$.zipCode.$error }" />
                                <div class="form-label text-danger" v-if="v$.zipCode.$error">Please enter a valid zip code</div>
                            </div>
                        <div class="form-outline mb-4">
                            <a class="text-dark" href="/profile/password">Change Password</a>
                        </div>
                        <div class="pt-3">
                            <input class="btn btn-warning text-white btn-lg btn-block" type="submit" value="Save Profile" />
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
    import { required, minLength, maxLength, numeric } from '@vuelidate/validators'

    export default {
        name: 'Profile',
        setup () {
            return { v$: useVuelidate() }
        },
        data() {
            return {
                email: '',
                displayName: '',
                zipCode: '',
                successMessage: null,
                failMessage: null,
            }
        },
        validations: {
            displayName: {
              required
            },
            zipCode: {
              required,
              numeric,
              minLength: minLength(6),
              maxLength: maxLength(6)
            }
        },
        mounted() {
            axios({method:'get', 
                    url: "http://localhost:8080/profile/retrieve", 
                    withCredentials: true
            })
            .then(response => {
                if (response != null) {
                    this.email = response.data.data.email
                    this.displayName = response.data.data.displayName
                    this.zipCode = response.data.data.zipCode
                }
            }).catch((error) => {
                this.failMessage = "Opps! Something went wrong. Please try again later."
                this.successMessage = null
            })
        },
        methods: {
            submit: function(e) {
                this.v$.$validate()
                if (!this.v$.$error) {
                    var profileForm = new FormData();
                    profileForm.append('displayName', this.displayName);
                    profileForm.append('zipCode', this.zipCode);
                    axios({ method:'post', 
                        url: "http://localhost:8080/profile/update", 
                        data: profileForm, 
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