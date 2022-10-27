<template>
    <section class="min-vh-100" style="background-color: #F4f7f7;">
        <div class="container py-5">
            <div class="row d-flex justify-content-center">
                <div class="col col-xl-10">
                    <div class="card mb-5">
                        <div class="card-body py-3 pb-0 px-4">
                            <h4 class="my-1"><strong>New Discussion Topic</strong></h4>
                            <hr class="my-4">
                        </div>
                        <div class="card-body py-0 px-4">
                            <form id="discussionForm" @submit.prevent="submit">
                                <p style="white-space: pre-line" class="fw-normal mb-3 alert alert-success"
                                    v-if="successMessage">{{ successMessage }}</p>
                                <p style="white-space: pre-line" class="fw-normal mb-3 alert alert-danger"
                                    v-if="failMessage">{{ failMessage }}</p>
                                <div class="form-outline mb-4">
                                    <label class="form-label" for="title">Discussion Title</label>
                                    <input type="text" id="title" name="title" v-model="title"
                                        class="form-control form-control-md"
                                        :class="{ 'border-danger': v$.title.$error }" />
                                    <div class="form-label text-danger" v-if="v$.title.$error">Please enter a title
                                    </div>
                                </div>
                                <div class="form-outline mb-4">
                                    <label class="form-label" for="message">Discussion Message</label>
                                    <VueQuill v-model:content="message" />
                                    <div class="form-label text-danger" v-if="v$.message.$error">Please enter a message
                                    </div>
                                </div>
                                <div class="pt-1 mb-4">
                                    <input class="btn btn-warning btn-md btn-block text-white" type="submit"
                                        value="Post Topic" />
                                </div>
                            </form>
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
import VueQuill from '../../components/VueQuill.vue'

export default {
    name: 'NewDiscussion',
    setup() {
        return { v$: useVuelidate() }
    },
    components: {
        VueQuill
    },
    data() {
        return {
            title: null,
            message: '',
            successMessage: null,
            failMessage: null,
        }
    },
    validations: {
        title: {
            required,
        },
        message: {
            required,
        },
    },
    mounted() {
        if (!this.$cookies.get('token')) {
            this.$router.push({ name: 'Login', query: { ref: 'newdiscussion'} })
        }
    },
    methods: {
        submit: function (e) {
            this.v$.$validate()
            if (!this.v$.$error) {
                var discussionForm = new FormData();
                discussionForm.append('title', this.title);
                discussionForm.append('message', this.message);
                axios({
                    method: 'post',
                    url: "http://localhost:8080/discussion/create",
                    data: discussionForm,
                    headers: { "Content-Type": "multipart/form-data" },
                    withCredentials: true
                }).then((res) => {
                    if (res.data.success)
                        this.$router.push('/discussion/' + res.data.data.id)
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
    }
}

</script>