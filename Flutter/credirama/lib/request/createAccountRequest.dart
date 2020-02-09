import 'dart:core';

class CreateAccountRequest {
    int owner;
    String contract;

  CreateAccountRequest(this.owner, this.contract);

  Map<String, String> toSend() {
    Map<String, String> result = new Map();
    result.putIfAbsent("owner", () => this.owner.toString());
    result.putIfAbsent("contract", () => this.contract);
    return result;
  }

  @override
  String toString() {
    return 'CreateAccountRequest{owner: $owner, contract: $contract}';
  }

}