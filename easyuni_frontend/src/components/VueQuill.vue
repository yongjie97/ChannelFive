<template>
    <QuillEditor v-model:content="message" contentType="html" theme="snow" />
</template>
  
<script>
import { QuillEditor } from '@vueup/vue-quill'
import '@vueup/vue-quill/dist/vue-quill.snow.css';

export default {
    components: {
        QuillEditor
    }
}
</script>

<style>
.ql-container {
    min-height: 150px;
}
</style>