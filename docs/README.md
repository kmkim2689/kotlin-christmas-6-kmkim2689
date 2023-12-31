# 🎄 크리스마스 프로모션

## 고려 사항

### 1. TDD 기반 개발

### 2. MVC 패턴 적용

## 구현 사항
### 0. 카테고리 및 메뉴, 혜택에 대한 Enum Class, Data Class 구현
- [x] Category Enum Class : 메뉴의 카테고리를 표현하기 위한 클래스로, 생성자로 카테고리명과 평일/주말 할인가격에 대한 정보를 담고 있음
- [x] Menu Enum Class : 사용자가 고를 수 있는 메뉴들이 자식 클래스로 존재하며, 생성자는 Category와 가격으로 구성
- [x] Badge Enum Class : 뱃지 종류 별로 존재하며, 생성자로 문자열 형태의 뱃지명이 존재
- [x] AdvantageItem Data Class : 혜택의 이름과 혜택 금액으로 이뤄짐
- [x] OrderItem Data Class : 사용자가 입력한 값을 토대로 구성되며, Menu와 주문 수량(정수)으로 구성
- [x] ReservationInfo Data Class : 사용자가 입력한 값을 토대로 구성되며, 예약일(정수)과 OrderItem의 리스트로 구성 

### 1. 고객이 12월 중 방문일을 입력하도록 하는 기능 (View → Controller → Domain)
- [x] InputView 클래스에서 기능 구현
  - [x] InputView에서 안내문구를 출력하고 값을 입력받아 정수 형태로 반환 
  - [x] 1부터 31까지의 정수만 입력할 수 있도록 하며, 그 이외의 값을 입력 시 예외 처리
  - [x] 문자열 형태로 입력받은 값을 검증하고 정수로 변환하는 로직은 InputViewMapper에서 확장함수로 구현
- [x] 입력한 값을 Controller가 ReservationInfo 클래스에 저장

### 2. 고객이 주문할 메뉴와 개수를 입력하도록 하는 기능 (View → Controller → Domain)
- [x] InputView 클래스에서 기능 구현
  - [x] 사용자가 올바른 형태로 메뉴명과 수량을 입력하도록 하고, 그 형식을 지키지 않았을 경우 예외 처리
    - [x] 반드시 `메뉴명-숫자` 형태로 입력하도록 한다.
    - [x] 메뉴판에 없는 메뉴를 입력하지 못하도록 한다.
    - [x] 중복된 메뉴를 입력할 수 없도록 한다.
    - [x] 입력 시 공백이 없어야 한다. 하지만 trim으로 처리하여 공백이 있어도 예외 처리하지 않고 공백을 제거하여 처리한다.
    - [x] 수량으로 1 이상 20 이하의 정수만 입력할 수 있도록 한다.(전체 주문 최대 20개)
    - [x] 총 수량이 20 초과가 될 수 없도록 한다.
  - [x] 검증 로직은 InputViewMapper, InputViewValidator 클래스에서 구현
- [x] 입력된 값을 Controller가 ReservationInfo 클래스에 저장
  - [x] 메뉴명과 수량으로 이뤄진 ReservationInfo 데이터 클래스 형태로 관리
  - [x] 이 과정에서, split를 활용하여 ","와 "-"를 기준으로 주문 메뉴와 수량을 분리하여 저장
  - [x] 수량은 정수로 변환하여 저장하는데, 문자열 형태로 입력받은 값을 정수로 변환하는 로직은 InputViewMapper에서 확장함수로 구현

### 3. 고객이 주문한 메뉴를 표출하는 기능 (Domain → Controller → View)
- [x] OutputView 클래스에서 출력 구현
  - [x] ReservationInfo 클래스로부터 Menu 데이터 클래스의 리스트 형태로 보존되어 있는 메뉴 정보를 가져와 한 줄씩 메뉴명과 수량을 출력한다.
  - [x] `"메뉴명" "수량"개` 형태로 표출한다.

### 4. 할인 전 총주문 금액을 표출하는 기능 (Domain → Controller → View)
- [x] ReservationInfo 클래스에서 총 주문 금액을 계산하는 메소드 구현
  - [x] Menu 데이터 클래스로 이뤄진 리스트를 순회하며 총 금액을 더해 최종 금액을 산출
- [x] Controller에서 1,000단위로 "," 표식 추가
- [x] OutputView 클래스에서 출력 구현
  

### 5. 증정 메뉴를 표출하는 기능 (Domain → Controller → View)
- [x] ReservationInfo에 저장되어 있는 할인 전 총 주문 금액을 활용하여 120,000 이상 시 true, 그렇지 않을 시 false 반환
- [x] Controller에서 증정 여부가 true일 때 "샴페인 1개", false일 때 "없음"을 OutputView에 전달 
- [x] OutputView 클래스에서 출력 구현

### 6. 혜택 내역을 계산하고 표출하는 기능 (Domain → Controller → View)
- [x] 모든 혜택 내역은 AdvantageItem으로 이뤄진 리스트 형태로 반환한다.
- [x] ReservationInfo의 할인 전 총 주문 금액을 바탕으로, 10,000원 이상인지 분기처리
  - [x] 10,000원을 넘지 못하면 바로 빈 리스트를 반환하고 그렇지 않다면 할인 로직을 진행
- [x] ReservationInfo 할인/혜택 로직을 구현 : 네 가지 대분류
  - [x] **<메뉴와 무관한 할인> <1일 ~ 25일> 디데이 할인**
    - [x] 입력받은 일에 따라 할인 금액을 산출
    - [x] n이 입력받은 일이라고 하면, 할인 금액은 `1000 + 100(n-1)원`
  - [x] **<메뉴와 무관한 할인> <1일 ~ 31일> 달력에 별이 있는 경우 특별 할인**
    - [x] 입력받은 일을 7로 나눈 나머지가 3인 날, 혹은 25일이라면 할인 금액 `1000원` 추가
  - [x] **<메뉴와 관련된 할인> <1일 ~ 31일> 메뉴 별 할인**
    - [x] ReservationInfo 내부에 저장되어 있는 요일이 일 ~ 목에 해당하면서 디저트 메뉴일 경우 2023 * 총 디저트 수량 만큼 할인
    - [x] ReservationInfo 내부에 저장되어 있는 요일이 금 ~ 토에 해당하면서 메인 메뉴일 경우 2023 * 총 메인메뉴 수량 만큼 할인
  - [x] 5.에서 산출된 증정 여부가 true라면 증정 이벤트로 25,000 추가, false라면 추가하지 않는다.
- [x] 디데이 할인금액, 평일 할인 금액, 주말 할인 금액, 특별 할인 금액, 증정 이벤트 금액으로 구성된 데이터 클래스 리스트를 반환
- [x] Controller가 Domain으로부터 총 혜택 금액을 가져와 View에 표출할 수 있는 문자열 형태로 가공
  - [x] 금액 앞에 "-" 문자열을, 금액 뒤에는 "원" 문자열을 추가
- [x] OutputView에서 출력

### 7. 총 혜택 금액을 계산하고 표출하는 기능 (Domain → Controller → View)
- [x] ResultCalculator에서 6.에서 산출된 각 혜택 금액(Advantage 데이터 클래스의 amount 값)을 모두 더한 금액을 저장
- [x] Controller은 Domain으로부터 총 혜택 금액을 가져와 View에 표출할 수 있는 문자열 형태로 가공
  - [x] 금액 앞에 "-" 문자열을, 금액 뒤에는 "원" 문자열을 추가
  - [x] 1,000단위로 "," 표식 추가
- [x] OutputView에서 출력

### 8. 할인 후 금액을 계산하고 표출하는 기능 (Domain → Controller → View)
- [x] ResultCalculator에서 할인 후 금액을 계산
- [x] Controller에서 금액 뒤에 "원"을 붙여 OutputView에 전달
- [x] OutputView에서 출력

### 9. 수령할 이벤트 배지를 표출하는 기능 (Domain → Controller → View)
- [x] ResultCalculator에서 수령할 이벤트 배지를 산출
- [x] 7.에서 산출된 총 혜택 금액에 따라 증정 배지를 분기 처리
- [x] 총 혜택 금액에 따라 "없음" / "별" / "트리" / "산타"를 증정 배지로 설정
- [x] OutputView에서 출력