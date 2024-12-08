// function Validator(options) {
//   function functionHanderDisplayRegister(
//     classNameRegister,
//     mainBoxCss,
//     resultResponse,
//     durationDisappear
//   ) {
//     const boxdisplay = document.querySelector(classNameRegister);
//     const newElement = document.createElement("div");

//     var resultHandler = [
//       {
//         boxImage: "--boxImageFail",
//         image: "fa-exclamation",
//         title: "Fail",
//         announcement: "Your are register unsuccessful, please check again",
//       },
//       {
//         boxImage: "--boxImageSuccess",
//         image: "fa-check",
//         title: "Success",
//         announcement: "Your are register successful",
//       },
//     ];
//     var deleteElement = setTimeout(function () {
//       boxdisplay.removeChild(newElement);
//     }, durationDisappear + 1000);
//     var stageRes = resultHandler[resultResponse ? 1 : 0];
//     var durationCalc = (durationDisappear / 1000).toFixed(2);
//     newElement.classList.add(mainBoxCss);
//     newElement.style.animation = `slideItem 1s ease, disappearHandler 0.5s ease ${durationCalc}s forwards`;
//     newElement.innerHTML = `
//             <div class="mainContainer__element ${stageRes.boxImage}">
//                   <i class="fa-solid ${stageRes.image}"></i>
//                     </div>
//                     <div class="mainContaine__element --boxInfomation">
//                 <p class="mainContainer__element__child --firstInfo">${stageRes.title}</p>
//                 <p class="mainContainer__element__child --secondInfo">${stageRes.announcement}</p>
//                </div>
//                <div class="mainContainer__element --closeBox">
//                 <i class="fa-solid fa-xmark"></i>
//                </div>
//             `;
//     boxdisplay.appendChild(newElement);
//     newElement.onclick = function (e) {
//       if (e.target.closest(".--closeBox")) {
//         boxdisplay.removeChild(newElement);
//         clearTimeout(deleteElement);
//       }
//     };
//   }
//   function getParentSpecific(childElement, selectorKey) {
//     while (childElement.parentElement) {
//       if (childElement.parentElement.matches(selectorKey)) {
//         return childElement.parentElement;
//       }
//       childElement = childElement.parentElement;
//     }
//   }
//   function functionHandlerValidator(keyId, formId) {
//     function handlerBlurEven() {
//       var textValid = "";
//       for (var functionHandler of handlerValid[keyId]) {
//         var textResponse = InputElement.value;
//         textValid = functionHandler(textResponse);
//         if (textValid !== "") {
//           break;
//         }
//       }
//       if (textValid) {
//         SpanElement.innerText = textValid;
//         SpanElement.classList.add(options.validDisplayText);
//         InputElement.classList.add(options.validDisplayBox);
//       } else {
//         SpanElement.innerText = "";
//         SpanElement.classList.remove(options.validDisplayText);
//         InputElement.classList.remove(options.validDisplayBox);
//       }
//       return textValid;
//     }
//     function checkedEveryBoxInput() {
//       const attribute = keyId;
//       var MainElement = document.querySelector(formId);
//       var InputElements = MainElement.querySelectorAll(
//         "input[type=" + attribute + "]"
//       );

//       var listInputElements = Array.from(InputElements);
//       var ParentElement = getParentSpecific(
//         InputElements[0],
//         options.parentSelector
//       );
//       //  console.log(ParentElement);
//       var SpanElement = ParentElement.querySelector("span");

//       var textValid = "";
//       var functionHandlerValid = handlerValid[attribute][0];
//       for (var elementBox of listInputElements) {
//         textValid = functionHandlerValid(elementBox);
//         if (textValid === "") {
//           break;
//         }
//       }
//       var newInputElement = ParentElement.querySelector(
//         options.setVaildCssOptionsBox
//       );
//       function addValidDisplay() {
//         SpanElement.innerText = textValid;
//         SpanElement.classList.add(options.validDisplayText);
//         newInputElement.classList.add(options.validDisplayBox);
//       }
//       function removeValidDisplay() {
//         SpanElement.innerText = "";
//         SpanElement.classList.remove(options.validDisplayText);
//         newInputElement.classList.remove(options.validDisplayBox);
//       }
//       if (textValid) {
//         addValidDisplay();
//       } else {
//         removeValidDisplay();
//       }
//       for (var i = 0; i < listInputElements.length; i++) {
//         listInputElements[i].onchange = function () {
//           removeValidDisplay();
//         };
//       }

//       listInputElements.forEach(function (element) {
//         element.onchange = function () {
//           // console.log('hello');
//           var someCheck = listInputElements.some(
//             (elementValue) => elementValue.checked
//           );
//           if (someCheck) {
//             removeValidDisplay();
//           } else {
//             addValidDisplay();
//           }
//         };
//       });

//       // listInputElements.forEach(function (element) {
//       //     // Hàm kiểm tra trạng thái của nhóm checkbox
//       //     function checkCheckboxGroup() {
//       //         // Kiểm tra nếu có ít nhất một checkbox được chọn
//       //         const anyChecked = listInputElements.some(elementValue => elementValue.checked);

//       //         if (!anyChecked) {
//       //             // Nếu không có checkbox nào được chọn, hiển thị thông báo lỗi
//       //             addValidDisplay();
//       //         } else {
//       //             // Nếu có ít nhất một checkbox được chọn, ẩn thông báo lỗi
//       //             removeValidDisplay();
//       //         }
//       //     }

//       //     // Gắn sự kiện onchange cho mỗi checkbox
//       //     element.onchange = checkCheckboxGroup;

//       //     // Kiểm tra trạng thái ban đầu của nhóm checkbox
//       //     checkCheckboxGroup();
//       // });

//       return textValid;
//     }

//     if (keyId === "radio" || keyId === "checkbox") {
//       return checkedEveryBoxInput;
//     } else {
//       var MainElement = document.querySelector(formId);
//       var InputElement = MainElement.querySelector(keyId);
//       var ParentElement = getParentSpecific(
//         InputElement,
//         options.parentSelector
//       );
//       // console.log(ParentElement);
//       var SpanElement = ParentElement.querySelector("span");

//       InputElement.onblur = handlerBlurEven;
//       InputElement.oninput = function () {
//         SpanElement.innerText = "";
//         SpanElement.classList.remove(options.validDisplayText);
//         InputElement.classList.remove(options.validDisplayBox);
//       };

//       return handlerBlurEven;
//     }
//   }

//   var formType = options.form;
//   var listRules = options.rules;
//   var mainElement = document.querySelector(formType);
//   var handlerValid = {};

//   listRules.forEach(function (objectResult) {
//     if (Array.isArray(handlerValid[objectResult.idInput])) {
//       handlerValid[objectResult.idInput].push(objectResult.handler);
//     } else {
//       handlerValid[objectResult.idInput] = [objectResult.handler];
//     }
//   });
//   // console.log(handlerValid);
//   var functionHanderValidation = {};
//   Object.keys(handlerValid).forEach(function (keyId) {
//     functionHanderValidation[keyId] = functionHandlerValidator(keyId, formType);
//   });
//   // console.log(functionHanderValidation);
//   var formElement = document.querySelector(options.formContainer);
//   console.log(formElement);
//   formElement.onsubmit = function (event) {
//     event.preventDefault();
//     var isValid = true;
//     Object.keys(functionHanderValidation).forEach(function (keyId) {
//       var validFunc = functionHanderValidation[keyId];
//       if (validFunc() !== "") {
//         isValid = false;
//       }
//     });
//     var resultIsVaild = isValid ? 1 : 0;
//     functionHanderDisplayRegister(
//       options.classNameDisplayRegister,
//       options.mainBoxCss,
//       resultIsVaild,
//       7000
//     );

//     if (isValid) {
//       function returnData() {
//         var listNode = formElement.querySelectorAll("[name]:not([disable])");
//         console.log(listNode);
//         var lists = Array.from(listNode);
//         var response = lists.reduce(function (objectValues, inputElement) {
//           if (Array.isArray(objectValues[inputElement.name])) {
//             if (inputElement.checked) {
//               objectValues[inputElement.name].push(inputElement.value);
//             }
//           } else {
//             objectValues[inputElement.name] = [inputElement.value];
//           }
//           return objectValues;
//         }, {});
//         return response;
//       }
//       options.getRespone(returnData());
//     }
//   };
// }
// Validator.isRequired = function (idInput, message) {
//   return {
//     idInput: idInput,
//     handler: function (valueText) {
//       //    var textValue = document.querySelector(idInput);
//       return valueText.trim() ? "" : message || "Please enter this box";
//     },
//   };
// };

// Validator.isRequiredBox = function (idInput, message) {
//   return {
//     idInput: idInput,
//     handler: function (valueText) {
//       //    var textValue = document.querySelector(idInput);
//       return valueText.checked ? "" : message || "Please chose this box";
//     },
//   };
// };
// Validator.isEmail = function (idInput, message) {
//   return {
//     idInput: idInput,
//     handler: function (valueText) {
//       var keyCheck = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
//       //    var textValue = document.querySelector(idInput);
//       return keyCheck.test(valueText)
//         ? ""
//         : message || "Please enter the correct email";
//     },
//   };
// };
// Validator.isRequiredPassword = function (idInput, message) {
//   return {
//     idInput: idInput,
//     handler: function (valueText) {
//       //    var textValue = document.querySelector(idInput);

//       return valueText.length >= 8
//         ? ""
//         : message || "Password must be greater than 8 characters";
//     },
//   };
// };
// Validator.isConfirmPassword = function (idInputConfirm, idInput, message) {
//   return {
//     idInput: idInputConfirm,
//     handler: function (valueText) {
//       //    var textValue = document.querySelector(idInput);
//       var textInput = document.querySelector(idInput).value;

//       return valueText === textInput
//         ? ""
//         : message || "Password is not the same";
//     },
//   };
// };

// export default Validator;
