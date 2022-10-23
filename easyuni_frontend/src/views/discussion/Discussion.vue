<template>
    <section class="min-vh-100" style="background-color: #E0E0E0;">
        <div class="container py-5">
            <div class="row d-flex justify-content-center">
                <div class="col col-xl-10">
                    <div class="card mb-5 custom">
                        <div class="card-body py-3 px-4">
                            <div class="row d-flex">
                                <div class="col col-l-8">
                                    <h4 class="mb-1"><strong>{{ title }}</strong></h4>
                                    <p class="small mb-0 text-muted">{{ displayName }}<span class="mx-2">|</span>{{ date
                                    }}</p>
                                </div>
                                <div class="col col-l-4" style="text-align: right">
                                    <router-link :to="this.$route.params.id + '/reply'"
                                        class="btn btn-warning text-white btn-md btn-block">Reply to
                                        this topic</router-link>
                                </div>
                                <hr class="my-4">
                                <p v-html="message"></p>
                            </div>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-header pt-3 px-4 text-white" style="background-color: #030614">
                            <h5><strong>REPLIES ({{size}})</strong></h5>
                        </div>
                    </div>
                    <div class="card-body" style="background-color: white">
                        <DiscussionReply v-for="(data, index) in replies" :index=index :size=replies.length :title="data.title"
                            :displayName="data.author.displayName" :date="data.date" :message="data.message" />
                    </div>
                </div>
            </div>
        </div>
    </section>
</template>

<script>

import moment from 'moment';

import DiscussionReply from '../../components/DiscussionReply.vue'

export default {
    name: 'Discussion',
    components: {
        DiscussionReply,
    },
    data() {
        return {
            id: '',
            title: '',
            displayName: '',
            date: '',
            message: '',
            size: 0,
            replies: [],
        }
    },
    computed: {
        date() {
            return moment(this.date).format('lll')
        }
    },
    mounted() {
        axios({
            method: 'get',
            url: "http://localhost:8080/discussion/" + this.$route.params.id,
        })
            .then(response => {
                if (response != null) {
                    this.id = response.data.data.id,
                        this.title = response.data.data.title,
                        this.date = response.data.data.date,
                        this.displayName = response.data.data.author.displayName,
                        this.message = response.data.data.message,
                        this.replies = response.data.data.replies,
                        this.size = response.data.data.replies.length
                } else {
                    this.$router.push('/404')
                }
            }).catch((error) => {
                this.$router.push('/404')
            })
    },
}

</script>

<style scoped>
.card {
    border-radius: 0;
    border: none;
}

.card-header {
    border-radius: 0;
    border: none;
}

.custom {
    box-shadow: 2px 5px 11px 0px rgba(0, 0, 0, 0.32);
    -webkit-box-shadow: 2px 5px 11px 0px rgba(0, 0, 0, 0.32);
    -moz-box-shadow: 2px 5px 11px 0px rgba(0, 0, 0, 0.32);
}
</style>