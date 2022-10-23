<template>
  <section class="min-vh-100" style="background-color: #E0E0E0;">
    <div class="container py-5">
      <div class="row d-flex justify-content-center">
        <div class="col col-xl-10">
          <div class="card mb-5">
            <div class="card-body py-3 px-4">
              <h4 class="my-1"><strong>Discussion Board</strong></h4>
              <p class="small mb-0 text-muted">General and university-related topics.</p>
              <hr class="my-4">
              <router-link class="btn btn-warning text-white btn-md btn-block" to="/discussion/new">New Discussion</router-link>
            </div>
          </div>
            <DiscussionListItem v-for="data in discussions" :id="data.id" :title="data.title" :displayName="data.author.displayName"
              :date="data.date" :numReplies="data.replies.length" :numViews="data.viewCount" />
        </div>
      </div>
    </div>
  </section>
</template>

<script>

import DiscussionListItem from '../../components/DiscussionListItem.vue'

export default {
  name: 'DiscussionList',
  components: {
    DiscussionListItem
  },
  data() {
    return {
      discussions: [],
    }
  },
  mounted() {
    axios({
      method: 'get',
      url: "http://localhost:8080/discussion"
    })
      .then(response => {
        if (response != null) {
          this.discussions = response.data.data
        }
      }).catch((error) => {

      })
  },
}
</script>