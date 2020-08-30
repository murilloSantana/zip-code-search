# Zip Code Search Documentation
<https://github.com/murilloSantana/zip-code-search>

## Version: 0.0.1-SNAPSHOT

**License:** [MIT](https://opensource.org/licenses/MIT)

### /address

#### GET
##### Summary

List all addresses

##### Responses

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | Listed addresses | [ [AddressDTO](#addressdto) ] |
| 500 | Some problem occurred on the server |  |

#### POST
##### Summary

Create new address

##### Description

In case of success, a URL is added to the Location header that allows the search for the newly created address

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| addressDTO | body | addressDTO | Yes | [AddressDTO](#addressdto) |

##### Responses

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | OK | string |
| 201 | Address created | string |
| 400 | Invalid Zip Code or missing required fields |  |
| 500 | Some problem occurred on the server |  |

### /address/zipcode/{zipcode}

#### GET
##### Summary

Retrieve address by Zip Code

##### Description

This API doesn't work with Zip Code with dashes, for example "22230-060", the Zip Code must be informed only with numbers such as "22230060"

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| zipcode | path | zipcode | Yes | string |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | Address found |
| 400 | Invalid Zip Code |
| 404 | Address not found |
| 500 | Some problem occurred on the server |

### /address/{id}

#### PUT
##### Summary

Update address

##### Description

The updated address is returned in the body

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| addressDTO | body | addressDTO | Yes | [AddressDTO](#addressdto) |
| id | path | id | Yes | long |

##### Responses

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | Address updated | [AddressDTO](#addressdto) |
| 400 | Invalid Zip Code or missing required fields or address not found |  |
| 500 | Some problem occurred on the server |  |

#### DELETE
##### Summary

Delete address

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| id | path | id | Yes | long |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | Address deleted |
| 400 | Address not found |
| 500 | Some problem occurred on the server |

### Models

#### AddressDTO

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| city | string |  | No |
| district | string |  | No |
| state | string |  | No |
| street | string |  | No |
| zipCode | string |  | No |
