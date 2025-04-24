// checkbox 클릭이 되면
// checkbox value, data-id 가져오기

// const com = document.querySelector("#completed")
// const label = document.querySelector(label)

// com.addEventListener("click", ()=>{
//     console.log(com.value)
// }

// )

// 이벤트 버블링 (자식의 이벤트가 부모에게 전달)
document.querySelector(".list-group").addEventListener("click", (e) => {
  // 어떤 label 안 checkbox 에서 이벤트가 발생했는지 확인
  const chk = e.target;
  console.log(chk);
  // console.log(chk.value);
  // checkbox 체크, 해제 여부확인
  console.log(chk.checked);

  // id 가져오기
  // closest("선택자") : 부모에서 제일 가까운 요소 찾기
  // data-이름 속성 가져오기 : dataset
  const id = chk.closest("label").dataset.id;
  console.log(id);

  // actionForm 찾은 요소들의 value 후 값 변경하기

  const actionFrom = document.querySelector("#actionForm");
  actionFrom.id.value = id;
  actionFrom.completed.value = chk.checked;

  actionFrom.submit();
});
