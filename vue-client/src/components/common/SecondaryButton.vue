<template>
  <button
      @click="$emit('click')"
  >
    {{ text }}
  </button>

</template>

<script>
export default {
  name: "SecondaryButton",
  props: {
    text: {
      required: true,
      type: String,
    }
  }
}
</script>

<style scoped lang="scss">
button {
  border-radius: 28px;
  background-color: #FFFFFF;
  color: #000000;
  font-size: 24px;
  padding: 12px 24px;
  border: 3px solid #000000;
  cursor: pointer;
  margin: 6px;
}

button:hover {
  background-color: darken(#FFFFFF, 8%);
}
</style>
