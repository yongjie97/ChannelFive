<template>
    <section class="min-vh-100" style="background-color: #F4f7f7;">
        <div class="container py-2 h-100">
            <div class="row d-flex justify-content-center align-items-center vh-100">
                <div class="col col-xl-10">
                    <div class="card">
                        <div class="row g-0">
                            <div class="col-md-6 col-lg-5 d-flex align-items-center bg-custom">
                                <div class="text-white py-4 p-md-5 mx-md-4 mx-4">
                                    <h4 class="mb-4 ">Login to join discussions</h4>
                                    <p class="small mb-0">Gain access to our popular discussion boards with countless
                                        topics to begin your university journey.</p>
                                </div>
                            </div>
                            <div class="col-md-6 col-lg-7 d-flex align-items-center">
                                <div class="card-body p-4 p-lg-5 text-black">

                                    <form id="loginForm" @submit.prevent="submit">
                                        <h4 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">Sign into your
                                            account</h4>
                                        <p style="white-space: pre-line" class="fw-normal mb-3 alert alert-success"
                                            v-if="successMessage">{{ successMessage }}</p>
                                        <p style="white-space: pre-line" class="fw-normal mb-3 alert alert-danger"
                                            v-if="failMessage">{{ failMessage }}</p>
                                        <div class="form-outline mb-4">
                                            <label class="form-label" for="email">Email address</label>
                                            <input type="email" id="email" v-model="email" name="email"
                                                class="form-control form-control-lg"
                                                :class="{ 'border-danger': v$.email.$error }" />
                                            <div class="form-label text-danger" v-if="v$.email.$error">Please enter a
                                                valid email</div>
                                        </div>

                                        <div class="form-outline mb-4">
                                            <label class="form-label" for="password">Password</label>
                                            <input type="password" id="password" v-model="password" name="password"
                                                class="form-control form-control-lg"
                                                :class="{ 'border-danger': v$.password.$error }" />
                                            <div class="form-label text-danger" v-if="v$.password.$error">Please enter
                                                your password</div>
                                        </div>

                                        <div class="pt-1 mb-4">
                                            <input class="btn btn-warning btn-lg btn-block text-white" type="submit"
                                                value="Login" />
                                        </div>

                                        <router-link class="small text-muted" to="/forgot_password">Forgot password?
                                        </router-link>
                                        <p class="mb-5 pb-lg-2 text-dark">Don't have an account?
                                            <router-link class="text-dark" to="/register">Register here</router-link>
                                        </p>
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
    name: 'Login',
    setup() {
        return { v$: useVuelidate() }
    },
    data() {
        return {
            email: null,
            password: null,
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
        }
    },
    beforeMount() {
        if (this.$route.query.message)
            this.successMessage = this.$route.query.message
        if (this.$cookies.get('token')) {
            if (this.$route.query.ref === "newdiscussion") {
                    this.$router.push("/discussion/new")
            } else if (this.$route.query.ref === "replydiscussion") {
                this.$router.push("/discussion/" + this.$route.query.discussionId + "/reply")
            } else {
                this.$router.push("/")
            }
        }
    },
    methods: {
        submit: function (e) {
            this.v$.$validate()
            if (!this.v$.$error) {
                var loginForm = new FormData();
                loginForm.append('email', this.email);
                loginForm.append('password', this.password);
                axios({
                    method: 'post',
                    url: "http://localhost:8080/login",
                    data: loginForm,
                    headers: { "Content-Type": "multipart/form-data" },
                    withCredentials: true
                }).then((res) => {
                    if (res.data.success) {
                        this.$router.go(0)
                    }
                    else {
                        this.successMessage = null;
                        this.failMessage = res.data.message;
                    }
                }).catch((error) => {
                    this.failMessage = "Opps! Something went wrong. Please try again later."
                    this.successMessage = null
                })
            }
        }
    },
}
</script>

<style scoped>
.bg-custom {
    background-image: url('../../assets/image/login.jpg')
}
</style>