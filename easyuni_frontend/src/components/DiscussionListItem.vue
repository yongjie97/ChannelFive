<template>
  <div class="card">
    <div class="card-body py-3 px-4">
      <h5 class="mb-1"><i class="fa-solid fa-comments small"></i> <router-link :to="id">{{ title }}</router-link></h5>
      <p class="small mb-0 text-muted">
        {{ displayName }}<span class="mx-2">|</span>{{ date }}<span class="mx-2">|</span>{{
        numReplies }} replies<span class="mx-2">|</span>{{ numViews }} views
      </p>
    </div>
  </div>
</template>

<script>

import moment from 'moment';

export default {
  name: 'DiscussionListItem',
  props: {
    id: String,
    title: String,
    displayName: String,
    date: String,
    numReplies: Number,
    numViews: Number,
  },
  computed: {
    date() {
      return moment(this.date).format('lll')
    },
    id() {
      return '/discussion/' + this.id
    }
  }
}
</script>

<style scoped>
a {
  color: inherit;
  font-family: inherit;
  font-style: inherit;
  font-weight: inherit;
  text-decoration: none;
}

a:hover {
  color: #ffc107;
}

.card {
  border-radius: 0;
}
</style>