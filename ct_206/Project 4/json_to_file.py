import json

data = {
  'id': 1,
  'name': 'JSON Test Object',
  'purpose': 'Testing ouput of JSON data to a file',
  'subObject': {
    'id': 2,
    'name': 'JSON subobject'
  },
  'author': 'Kirin Patel'
}

json.dump(data, open('data.json', 'w'))