export function isClient(){
  //  alert(localStorage.getItem("role") !== "EMPLOYEE");
    return localStorage.getItem("role") !== "EMPLOYEE";
}