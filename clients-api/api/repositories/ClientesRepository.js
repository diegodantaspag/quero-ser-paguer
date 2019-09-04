const uuidv4 = require('uuid/v4');
const BaseRepository = require('./BaseRepository');
const { Cliente } = require('./../models');

class ClienteRepository extends BaseRepository {
  constructor() {
    super(Cliente);
  }

  findOrCreate(data) {
    if (data.email) {
      const dataInsert = {
        id: uuidv4(),
        ...data,
      };
      return this.model.findOrCreate(
        { where: { cpf: dataInsert.cpf }, defaults: { ...dataInsert } },
      );
    }
    return null;
  }
}

module.exports = ClienteRepository;
